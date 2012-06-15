/**
 * Created by IntelliJ IDEA.
 * User: ZQ
 * Date: 12-6-9
 * Time: 下午6:36
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function () {
    //just for demo
    $('.newsTitle').not('.consultList').attr('href', '/Hospital/News/NewsDetail.shtml').attr('target', '_blank');
    $('.consultList li a').attr('href', '/Hospital/Patient/Result.shtml').attr('target', '_blank');
    $('.Schedule').find('.paTr:odd').addClass('SchTrPaOdd');
    $('.Schedule').find('.paTr:even').addClass('SchTrPaEven');
    $('.docIntro a').attr('href', '/Hospital/Doctor/DocPer.shtml');

    /* nav click */
    $('.greybox li').live("click", function() {
        var href = $(this).find('a').attr('href');
        window.location.href = href;
    });
});