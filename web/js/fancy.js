/**
 * Created by thomas on 31/05/2017.
 */
$(function() {

    $('table').hide().slideDown();
    var $tr = $('tr');
    $tr.hide().each(function(index) {
        $(this).delay(700 * index).fadeIn(700);
    });

});