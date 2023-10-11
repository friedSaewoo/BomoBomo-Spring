
console.log("안녕");
$('.text-page p').on('mouseover',function(){
    console.log("안녕");
    $(this).css('color','white');
    $(this).css('backgroundColor','#FF7000');

})

$('.text-page p').on('mouseout',function(){
    console.log("안녕");
    $(this).css('color','black');
    $(this).css('backgroundColor','white');

})

$('.event-area').on('mouseover',function(){
    console.log("안녕");
    $(this).css('color','white');
    $(this).css('backgroundColor','#FF7000');

})

$('.event-area').on('mouseout',function(){
    console.log("안녕");
    $(this).css('color','black');
    $(this).css('backgroundColor','white');

})

$('.event-area').on('click',function(){

    window.location.href="/mypage/buyEvent";

})

