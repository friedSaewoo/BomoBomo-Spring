function loadImage() {
    let eventImgUploadPath = document.getElementById('eventUploadPath').value;
    let eventImgUuid = document.getElementById('eventImgUuid').value;
    let eventImgName = document.getElementById('eventImgName').value;
    let imagePath = eventImgUploadPath + '/' + eventImgUuid + '_' + eventImgName;

    let img = new Image();
    img.onload = function() {
        let eventImgDiv = $('<div class="event-img">');
        eventImgDiv.append(img);

        document.querySelector('.event-img').appendChild(eventImgDiv[0]);
    };
    img.src = "/admin/rest/displayEventImg?fileName=" + imagePath;
}
function loadDetail(){
    let eventDetailUploadPath = document.getElementById('eventDetailUploadPath').value;
    let eventDetailUuid = document.getElementById('eventDetailUuid').value;
    let eventDetailName = document.getElementById('eventDetailName').value;
    let imagePath = eventDetailUploadPath + '/' + eventDetailUuid + '_' + eventDetailName;

    let img = new Image();
    img.onload = function() {
        let eventDetailDiv = $('<div class="event-detail-img">');
        eventDetailDiv.append(img);

        document.querySelector('.event-detail-img').appendChild(eventDetailDiv[0]);
    };
    img.src = "/admin/rest/displayEventDetail?fileName=" + imagePath;
}
loadImage();
loadDetail();

let eventNumber = $('.event-num').val();
document.getElementById('board-delete').addEventListener('click', function(e) {
    e.preventDefault();
    console.log(eventNumber);
    if (confirm('정말 삭제하시겠습니까?')) {
        window.location.href = "/admin/adminEventDelete?eventNumber=" + eventNumber;
    } else {
    }
});