//댓글 리스트 불러오기
export function getListPage(listInfo, callback){
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
export function modify(sitterCommentNumber, replyInfo, callback){
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
export function addReply(reply, callback){
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
export function remove(sitterCommentNumber, callback){
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




