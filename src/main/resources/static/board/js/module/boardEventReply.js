//댓글리스트 불러오기
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


