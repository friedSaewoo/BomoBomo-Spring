export function getListPage(listInfo,callback){

    $.ajax({

        url:`/eventReplies/list/${listInfo.eventBoardNumber}/${listInfo.page}`,
        type:'get',
        dataType:'json',
        success : function (result){
            console.log(result.replyList);
            console.log(result.pageReplyVo);
            console.log(result.totalReply);

            if(callback){
                callback(result);
            }
        }, error : function (a,b,c){
            console.error(c)
        }

    });
}

//댓귿 등록
export function addReply(reply, callback){
    $.ajax({

        url:'/eventReplies',
        type:'post',
        data:JSON.stringify(reply),
        contentType:'application/json; charset=utf-8',
        success : function (result){
            if(callback){
                callback(result);
            }
        }, error : function (a,b,c){
            console.error(c);
        }


    })
}


//댓글 삭제
export function deleteReply(eventCommentNumber, callback){
    $.ajax({

        url:`/eventReplies/${eventCommentNumber}`,
        type:'delete',
        success : function (result){

            if(callback){
                callback(result);
            }
        }, error : function (a,b,c){
            console.error(c);

        }

    })
}


//댓글 수정
export function modifyEventReply(eventCommentNumber, replyInfo, callback){

    $.ajax({

        url:`/eventReplies/${eventCommentNumber}`,
        type:'patch',
        data : JSON.stringify(replyInfo),
        contentType:'application/json; charset=utf-8',
        success : function (result){
            if (callback){
                callback(result);
            }
        },error : function (a,b,c){
            console.error(c);
        }

    })
}

export function showReply(result){
    let totalReply='';
    let text='';


    totalReply='댓글 '+result.totalReply +'개';

    result.replyList.forEach(r =>{




        text += `
            
     <dl>
        <dt>
            <div class="reply-box__writer" data-replyNum="${r.eventCommentNumber}">
                <strong>${r.userId}</strong>
            </div>
        </dt>

        <dd>
            <div class="reply-list-wrap">
                <div class="reply">
                    <div class="reply-box">
                        <div class="reply-box__content">${r.eventCommentContent}</div>
                    </div>
                     <div class="reply-btn-box">
                    `;

        if(r.userNumber == loginNumber){
            text+=  `
                        <span class="reply-btns"></span>
                        <div class="reply-btns__box none">
                            <div class="reply-remove-btn" data-deletenum="${r.eventCommentNumber}">삭제</div>
                            <div class="reply-modify-btn" data-modifynum="${r.eventCommentNumber}" >수정</div>
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

    //댓글이 한 개도 없으면 숨김
    if(result.totalReply!=0){
        $('.totalReply').css('display','block')
        $('.totalReply').html(totalReply);

    }else {
        $('.totalReply').css('display','none')
    }


    $('.review-reply').html(text);
    updatePagination(result.pageReplyVo);


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