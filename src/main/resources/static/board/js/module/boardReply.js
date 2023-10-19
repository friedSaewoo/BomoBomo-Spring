
//이벤트
//댓글리스트 불러오기
export function getListEventReviewPage(listInfo,callback){

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
export function addEventReviewReply(reply, callback){
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
export function removeEventReviewReply(eventCommentNumber, callback){
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
export function modifyEventReviewReply(eventCommentNumber, replyInfo, callback){

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



//서비스
//댓글 리스트 불러오기
export function getListServiceReviewPage(listInfo, callback){
    $.ajax({

        url : `/replies/list/${listInfo.sitterBoardNumber}/${listInfo.page}`,
        type : 'get',
        dataType : 'Json',
        success:function (result){
            console.log(result.replyList);
            console.log(result.pageReplyVo);
            console.log(result.totalReply);


            if(callback){
                callback(result);
            }

        }, error : function (a,b,c){
            console.error(c);
        }

    });
}


//댓글 수정
export function modifyServiceReviewReply(sitterCommentNumber, replyInfo, callback){
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

//댓글 등록
export function addServiceReviewReply(reply, callback){
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

//댓글 삭제
export function removeServiceReviewReply(sitterCommentNumber, callback){
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





