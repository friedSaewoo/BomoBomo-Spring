// //페이징처리
// export function updatePagination(pageReviewVo, $pageSection) {
//     // let $pagenation = $('.review-pagenation-container ul');
//     $pageSection.empty();
//
//     if (pageReviewVo.prev) {
//         $pageSection.append(`
//                 <li class="page-num"><a href="#" data-reviewnum="${pageReviewVo.startPage-1}">&lt;</a></li>
//             `);
//     }
//
//
//
//     //게시물이 1개도 존재하지 않는다면 페이징 표시 x
//     //한 개라도 존재할 때 페이징 번호가 나타난다.
//     if(pageReviewVo.realEnd!=0){
//         for (let page = pageReviewVo.startPage; page <= pageReviewVo.endPage; page++) {
//             if(page == pageReviewVo.criteria.page){
//                 $pageSection.append(`
//                     <li class="page-num active-page"><a href="#" class="on" data-reviewnum="${page}">${page}</a></li>
//                 `);
//
//             }
//             else{
//                 $pageSection.append(`
//                     <li class="page-num"><a href="#" class="on" data-reviewnum="${page}">${page}</a></li>
//                 `);
//             }
//         }
//     }else{
//         `<li></li>`
//     }
//
//
//
//
//     if (pageReviewVo.next) {
//         pageSection.append(`
//             <li class="page-num"> <a href="#" data-reviewnum="${pageReviewVo.endPage+1}">&gt;</a></li>
//             `);
//     }
// }