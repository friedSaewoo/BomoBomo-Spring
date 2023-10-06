
export function getSitterReviewList (page, callback){
    $.ajax({
        url : `/myPages/sitterReviewList`,
        type : 'get',
        data : {page : page},
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : function (a,b,c){
            console.error(c);
        }
    });
}

export function getEventReviewList (pages, callback){
    $.ajax({
        url : `/myPages/eventReviewList`,
        type : 'get',
        data : {pages : pages},
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : function (a,b,c){
            console.error(c);
        }
    });
}



//마이페이지 매칭인원 등록
export function showMtpageMatch(userNumber, callback){
    $.ajax({

        url:`/myPages/matchEmpInfo/${userNumber}`,
        type:'post',
        dataType: 'json',
        success : function (){
            if(callback){
                callback();
            }
        }, error : function (a,b,c){
            console.error(c);
        }



    })
}


