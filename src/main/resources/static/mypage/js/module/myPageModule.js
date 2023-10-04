
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