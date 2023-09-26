//input hidden에 넣어놓은 값 가져오기
let sitterBoardNumber = $('.reviewDetail-num').val();
console.log(sitterBoardNumber);


//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    const page = $(this).data('pagenum');
    getListPage({sitterBoardNumber, page},showReply);


    $('.page-num a').removeClass('active-page');
    $(this).addClass('active-page');
});


$('.btn-reply').on('click', function () {
    let content = $('#reply-content').val();

    if(!(content)){
        alert('내용을 입력해주세요');
        return;
    }

    let replyObj = {
        sitterCommentContent : content,
        sitterBoardNumber : sitterBoardNumber,
        userNumber : loginNumber
    };

    addReply(replyObj, function(){
        getListPage({sitterBoardNumber,page:1}, showReply);
    });

    $('#reply-content').val('');
});


let page = 1;
getListPage({sitterBoardNumber,page:1}, showReply);




let listInfo = {

    sitterBoardNumber : sitterBoardNumber,
    page : 1

}

function getListPage(listInfo, callback){
    $.ajax({

        url : `/replies/list/${listInfo.sitterBoardNumber}/${listInfo.page}`,
        type : 'get',
        dataType : 'Json',
        success:function (result){
            console.log(result.replyList);
            console.log(result.pageReplyVo);


            if(callback){
                callback(result);
            }

        }, error : function (a,b,c){
            console.error(c);
        }

    });
}

function showReply(result){
    let text='';

        result.replyList.forEach(r =>{
            text += `
            
     <dl>
        <dt>
            <div class="reply-box__writer" data-replyNum="${r.sitterCommentNumber}">
                <strong>${r.userId}</strong>
            </div>
        </dt>

        <dd>
            <div class="reply-list-wrap">
                <div class="reply">
                    <div class="reply-box">
                        <div class="reply-box__content">${r.sitterCommentContent}</div>
                    </div>
                     <div class="reply-btn-box">
                    `;

            if(r.userNumber == loginNumber){
              text+=  `
                        <span class="reply-btns"></span>
                        <div class="reply-btns__box none">
                            <div class="reply-remove-btn" data-deletenum="${r.sitterCommentNumber}">삭제</div>
                            <div class="reply-modify-btn" data-modifynum="${r.sitterCommentNumber}" >수정</div>
                        </div>
                `;
            }

            text+=
                    `                        
                    </div>
                </div>
            </div>
        </dd>
     </dl>                         
            `;
        })

    $('.review-reply').html(text);
    updatePagination(result.pageReplyVo);


}

//댓글 수정 삭제버튼 팝업
$('.review-reply').on('click', '.reply-btns', function () {
    let $replyBtnBox = $(this).closest('.reply-btn-box').find('.reply-btns__box');
    $('.reply-btns__box').addClass('none');
    $replyBtnBox.toggleClass('none');
});


$('body').click(function (e) {
    if ($(e.target).hasClass('reply-btns')) {
        //console.log('aa');
        return;
    }
    if (!$('.reply-btns__box').has(e.target).length) {
        $('.reply-btns__box').addClass('none');
    }
});


//댓글 수정창 팝업
$('.review-reply').on('click', '.reply-modify-btn', function () {
    let $content = $(this).closest('.reply').find('.reply-box__content');
    $content.replaceWith(`
      <div class='modify-box'>
        <textarea class='modify-content'>${$content.text()}</textarea>
        <button type='button' class='modify-content-btn'>수정 완료</button>
      </div>
    `);
    $('.reply-btns__box').addClass('none');
});


// 리플 삭제 버튼 처리
$('.review-reply').on('click', '.reply-remove-btn', function () {
    $('.reply-btns__box').addClass('none');

    let sitterCommentNumber = $(this).closest('.reply').find('.reply-remove-btn').data('deletenum');

    remove(sitterCommentNumber, function (){
        getListPage({sitterBoardNumber,page:1}, showReply);
    });
});


function remove(sitterCommentNumber, callback){
    $.ajax({

        url:`/replies/${sitterCommentNumber}`,
        type:'delete',
        success : function (result){
            if(callback){
                callback(result);
            }
        },error : function (a,b,c){
            console.error(c);
        }


    })
}

// 리플 수정 완료 처리
$('.review-reply').on('click', '.modify-content-btn', function () {
    console.log('modify!!!');
    let sitterCommentNumber = $(this).closest('.reply').find('.reply-modify-btn').data('modifynum');
    let replyContent = $(this).closest('.modify-box').find('.modify-content').val();
    // console.log(replyContent);
    let replyObj = {
        sitterCommentContent : replyContent
    };

    modify(sitterCommentNumber, replyObj, function (){
        getListPage({sitterBoardNumber,page:1}, showReply);
    });
});

function modify(sitterCommentNumber, replyInfo, callback){
    $.ajax({

        url:`/replies/${sitterCommentNumber}`,
        type:'patch',
        data : JSON.stringify(replyInfo),
        contentType: 'application/json; charset=utf-8',
        success : function (result){
            if(callback){
                callback(result)
            }
        }, error : function (a,b,c){
            console.error(c);
        }

    })

}

function addReply(reply, callback){
    $.ajax({

        url:'/replies',
        type:'post',
        data: JSON.stringify(reply),
        contentType:' application/json; charset=utf-8',
        success : function (){
            if(callback){
                callback();
            }
        }, error : function (a,b,c){
            console.error(c);
        }



    })
}

// 페이징처리
function updatePagination(pageReplyVo) {
    let $pagenation = $('.reply-pagenation-container ul');
    $pagenation.empty();

    if (pageReplyVo.prev) {
        $pagenation.append(`
                <li class="page-num"><a href="#" data-pagenum="${pageReplyVo.startPage-1}">&lt;</a></li>
            `);
    }


    //게시물이 1개도 존재하지 않는다면 페이징 표시 x
    //한 개라도 존재할 때 페이징 번호가 나타난다.
    if(pageReplyVo.realEnd!=0){
        //한 개라도 있으면 높이값 고정
        $('.review-reply').css('height','500px');
        for (let page = pageReplyVo.startPage; page <= pageReplyVo.endPage; page++) {
            if(page == pageReplyVo.criteria.page){
                $pagenation.append(`
                    <li class="page-num active-page"><a href="#" data-pagenum="${page}">${page}</a></li>
                `);
            }else {
                $pagenation.append(`
                    <li class="page-num "><a href="#" data-pagenum="${page}">${page}</a></li>
                `);

            }

        }
    }else{
        `<li></li>`
    }


    if (pageReplyVo.next) {
        $pagenation.append(`
            <li class="page-num"> <a href="#" data-pagenum="${pageReplyVo.endPage+1}">&gt;</a></li>
            `);
    }
}





