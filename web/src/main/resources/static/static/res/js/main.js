(function($) {
"use strict";

/*------------------------------------------------------------------
[Table of contents]
������ȫ��õ�Bootstrapģ�壺http://www.jqueryfuns.com
/*-------------------------------------------
menu  activation
--------------------------------------------- */
$('.humbargar').on('click', function () {
	$('.humbargar').addClass('remove');
	$('.close-area').addClass('active');
	$('.sidebar-menu').addClass('highlight');	
		
});
$('.close-area').on('click', function () {
	$('.humbargar').removeClass('remove');
	$('.humbargar').addClass('active');	
	$('.close-area').addClass('remove');
	$('.sidebar-menu').removeClass('highlight');	
		
});
$('.humbargar').on('click', function () {
	$('.humbargar').addClass('remove');
	$('.close-area').addClass('active');
	$('.close-area').removeClass('remove');	
});
$('.sub-menu a span ').on('click', function () {
	$(".sub-menu .sub-menu-item").slideToggle();
	return false	
});


/*-------------------------------------------
  js scrollup
--------------------------------------------- */
$.scrollUp({
	scrollText: '<i class="fa fa-angle-up"></i>',
	easingType: 'linear',
	scrollSpeed: 900,
	animation: 'fade'
}); 


})(jQuery);