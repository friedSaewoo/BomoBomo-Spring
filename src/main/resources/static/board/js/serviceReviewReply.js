
let sitterBoardNumber = $('.reviewDetail-num').val();


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

//댓글리스트
function showServiceReviewReply(sitterBoardNumber, callback){

    $.ajax({
        url:`/replies/list/${sitterBoardNumber}`,
        type:'get',
        dataType:'JSON',
        success : function (result){
            if(callback){
                callback(result)
            }

            console.log(result)
        }, error : function (a,b,c,){
            console.error(c);
        }



    })
}

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
                        <span class="reply-btns"></span>
                        <div class="reply-btns__box none">
                            <div class="reply-remove-btn">삭제</div>
                            <div class="reply-modify-btn">수정</div>
                        </div>
                    </div>
                </div>
            </div>
        </dd>
     </dl>
              
            
            `;
        })

    $('.review-reply').html(text);


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