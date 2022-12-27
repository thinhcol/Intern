// function updateFancyBox() {
//     jQuery("a[href$='.JPEG'], a[href$='.jpg'], a[href$='.jpeg'], a[href$='.JPG'], a[href$='.png'], a[href$='.PNG'], a[href$='.gif'], a[href$='.GIF'], a[href$='.bmp'], a[href$='.BMP']").each(function() {
//         if (!jQuery(this).attr('data-fancybox')) {
//             var key = "";
//             if (jQuery(this).parents('[displayfor]').length > 0) {
//                 key = jQuery(jQuery(this).parents('[displayfor]')[0]).attr('displayfor');
//             }
//             var subKey = "spro-images";
//             if (jQuery(this).parents('.attachment-thumb').length > 0) {
//
//             } else {
//                 if (jQuery(this).parents('.hiddenInfoRow').length > 0) {
//                     subKey = jQuery(this).parents('.hiddenInfoRow')[0].id;
//                 } else if (jQuery(this).parents('.displayInfoRow').length > 0) {
//                     subKey = jQuery(this).parents('.displayInfoRow')[0].id;
//                 } else if (jQuery(this).parents('.activity-comment').length > 0) {
//                     subKey = jQuery(this).parents('.activity-comment')[0].id;
//                 }
//                 jQuery(this).attr('data-fancybox', key + subKey);
//             }
//         }
//     });
//
//     jQuery("a[href$='.pdf'], a[href$='.PDF']").unbind('click').click(function(){
//         if (this.href.toLowerCase().startsWith("https://")) {
//             const uA = navigator.userAgent;
//             const vendor = navigator.vendor;
//
//             var isMobile = /android|iphone|kindle|ipad|iPad|iPhone/i.test(navigator.userAgent)
//                 || (/Safari/i.test(uA) && /Apple Computer/.test(vendor) && !/Mobi|Android/i.test(uA));
//
//             if (isMobile) {
//                 window.open(this.href , '_blank');
//             } else {
//                 jQuery.fancybox.open({
//                     src  : this.href ,
//                     type : 'iframe'
//                 });
//             }
//         } else {
//             window.open(this.href , '_blank');
//         }
//
//         return false;
//     });
//
//     jQuery("a.fancybox").unbind('click').click(function(){
//         var link = jQuery(this).parent().find("input").val();
//         if (link != undefined && link != ""  && link.toLowerCase().startsWith("https://")) {
//             jQuery.fancybox.open({
//                 src  : link,
//                 type : 'iframe'
//             });
//         } else {
//             window.open(link, '_blank');
//         }
//         return false;
//     });
// }
// const $ = document.querySelector.bind(document);
// const $$ = document.querySelectorAll.bind(document);
//
// const origin = location.origin;
//
// const imgs = $$('.list img');
//
// let img1 = $('.icon-menu_1');
// let img2 = $('.icon-menu_2');
// let img3 = $('.icon-menu_3');
// let img4 = $('.icon-menu_4');
// let img5 = $('.icon-menu_5');
// let img6 = $('.icon-menu_6');
//
// const tabs = $$('.list');
//
// tabs.forEach((tab, index) => {
//
//     tab.onclick = function() {
//         console.log(tab.classList.value);
//
//         if (tab.classList.value == 'list') {
//             img1.src = `${origin}/checkfile/src/main/resources/static/images/flow-chart (2) 1.png`;
//             img2.src = `${origin}/checkfile/src/main/resources/static/images/help 1.png`;
//         } else {
//             img1.src = `${origin}/checkfile/src/main/resources/static/images/flow-chart.png`;
//             img2.src = `${origin}/checkfile/src/main/resources/static/images/assignment .png`;
//         }
//
//         $('.list.focus').classList.remove('focus');
//         this.classList.add('focus');
//     }
// })
//
//
//
//
