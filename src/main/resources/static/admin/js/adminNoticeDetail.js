document.getElementById('board-delete').addEventListener('click', function(e) {
        e.preventDefault();
        let noticeNumber = $('#noticeNumber').val();
        console.log(noticeNumber);
        if (confirm('정말 삭제하시겠습니까?')) {
            window.location.href = "/admin/adminNoticeDelete?noticeNumber=" + noticeNumber;
        } else {
        }
    });