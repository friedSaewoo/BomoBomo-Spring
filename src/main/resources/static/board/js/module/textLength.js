//입력된 글자 바이트 계산
export function getTextLength(text) {
    let len = 0;
    for (let i = 0; i < text.length; i++) {
        if (escape(text.charAt(i)).length == 6) {
            len++;
        }
        len++;
    }
    return len;
}


//댓글 입력창 글자 수 제한
export function limitText(){
    $("#reply-content").keyup(function(e) {
        let contents = $('#reply-content').val();

        $(".word-count").html(`<span class="overWrite">  ${getTextLength(contents) + ' / 200'}  </span>`); //실시간 글자수 카운팅
        if (getTextLength(contents) > 200) {
            $('.overWrite').css('color', 'red')
        }
    });
}


//댓글 수정 입력창 글자 수 제한
export function limitModifyText(){
    $('.review-reply').on('keyup', '.modify-content', function (){
        let modifyContent = $('.modify-content').val();
        console.log(getTextLength(modifyContent) );
        $('.textLengthCheck').html(`<span class="overWrite">  ${getTextLength(modifyContent) + ' / 200'} </span>`); //실시간 글자수 카운팅
        if (getTextLength(modifyContent) > 200) {
            $('.overWrite').css('color', 'red')
        }
    })
}

