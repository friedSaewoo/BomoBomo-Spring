
let sitterBoardNumber = $('.reviewDetail-num').val();
console.log(sitterBoardNumber);

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
        showServiceReviewReply(sitterBoardNumber, showReply);
    });

    $('#reply-content').val('');
});



showServiceReviewReply(sitterBoardNumber, showReply);

// 댓글리스트
function showServiceReviewReply(sitterBoardNumber, callback){

    $.ajax({
        url:`/replies/list/${sitterBoardNumber}`,
        type:'get',
        dataType:'JSON',
        success : function (result){
            if(callback){
                callback(result)
            }

        }, error : function (a,b,c,){
            console.error(c);
        }



    })
}
// function getListPage(sitterBoardNumber, page, callback){
//     $.ajax({
//
//         url : `/replies/list/${sitterBoardNumber}/${page}`,
//         type : 'get',
//         dataType : 'Json',
//         success:function (result){
//             console.log(result.replyReviewVo);
//             console.log(result.pageReplyVo);
//
//
//             if(callback){
//                 callback(result);
//             }
//
//         }, error : function (a,b,c){
//             console.error(c);
//         }
//
//     });
// }

function showReply(result){
    let text='';

        result.forEach(r =>{
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
        showServiceReviewReply(sitterBoardNumber, showReply);
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
        showServiceReviewReply(sitterBoardNumber, showReply);
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

//페이징처리
// function updatePagination(pageReplyVo) {
//     let $pagenation = $('.reply-pagenation-container ul');
//     $pagenation.empty();
//
//     if (pageReplyVo.prev) {
//         $pagenation.append(`
//                 <li class="page-num"><a href="#" data-pagenum="${pageReplyVo.startPage-1}">&lt;</a></li>
//             `);
//     }
//
//     for (let page = pageReplyVo.startPage; page <= pageReplyVo.endPage; page++) {
//         $pagenation.append(`
//                     <li class="page-num "><a href="#" class="on" data-pagenum="${page}">${page}</a></li>
//                 `);
//
//     }
//     if (pageReplyVo.next) {
//         $pagenation.append(`
//             <li class="page-num"> <a href="#" data-pagenum="${pageReplyVo.endPage+1}">&gt;</a></li>
//             `);
//     }
// }