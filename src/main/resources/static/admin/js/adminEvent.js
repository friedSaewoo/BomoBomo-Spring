// var eventItems = document.querySelectorAll('.event-item');
//
// eventItems.forEach(function(eventItem) {
//     eventItem.addEventListener('click', function() {
//         var url = 'admin_event_detail.html';
//         window.location.href = url;
//     });
// });

let eventRegist = $('.regist-btn');
eventRegist.on('click', function() {
    let url = `/admin/eventRegist`;
    window.location.href = url;
});