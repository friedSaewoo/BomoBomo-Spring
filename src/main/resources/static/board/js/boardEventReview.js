//이벤트 후기 게시판 이동
$('.service-review-btn').on('click', function (){
    window.location.href="/board/serviceReview";
})


function loadPage(page, callback){


    $.ajax({

        url:`/reviews/eventReview/${page}`,
        type:'get',
        dataType:'json',
        success : function (result){





        }



    })


}