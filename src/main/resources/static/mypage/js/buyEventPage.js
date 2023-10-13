$('.text-page-2 p').on('mouseover',function(){
    console.log("안녕");
    $(this).css('color','white');
    $(this).css('backgroundColor','#FF7000');

})

$('.text-page-2 p').on('mouseout',function(){
    console.log("안녕");
    $(this).css('color','black');
    $(this).css('backgroundColor','white');

})


$('.sitter-area').on('mouseover',function(){
    console.log("안녕");
    $(this).css('color','white');
    $(this).css('backgroundColor','#FF7000');

})

$('.sitter-area').on('mouseout',function(){
    console.log("안녕");
    $(this).css('color','black');
    $(this).css('backgroundColor','white');

})
$('.sitter-area').on('click',function(){

    window.location.href="/mypage/buy";


})



