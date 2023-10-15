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


//댓글 작성일자
export function timeForToday(value, callback){
    const today = new Date();
    const timeValue = new Date(value);



    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);

    if(betweenTime <1){ return "방금 전";}

    if(betweenTime <60){ return `${betweenTime}분 전`}

    const betweenTimeHour = Math.floor(betweenTime/60);

    if(betweenTimeHour<24) {return `${betweenTimeHour}시간 전`}

    const betweenTimeDay = Math.floor((betweenTimeHour)/24);

    if(betweenTimeDay <365) {
        return `${betweenTimeDay}일 전`
    }
    return `${Math.floor(betweenTimeDay/365)}년 전`


}