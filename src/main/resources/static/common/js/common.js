//
//
// $(document).ready(function() {
//
//
//     $(window).scroll(function (){
//         let topBtn = $(this).scrollTop()>=100;
//
//         if(topBtn==0){
//             $('.top-btn button').hide();
//
//         }
//         else if(topBtn){
//             $('.top-btn button').show();
//
//         }
//
//     })
//
//     let floatPosition = parseInt($(".top-btn").css('top'));
//     $(window).scroll(function() {
//         let scrollTop = $(window).scrollTop();
//         let attachedPosition = scrollTop + floatPosition + "px";
//
//
//         $(".top-btn").css('top', attachedPosition);
//
//
//     }).scroll();
//
// });
//
//
//
//
// $('.top-btn button').click(function(){
//     $(window).scrollTop(0,0);
//
// })