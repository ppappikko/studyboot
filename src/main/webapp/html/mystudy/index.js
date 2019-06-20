var pageNo = 1,
pageSize = 6,
tbody,
prevPageLi,
nextPageLi,
param = location.href.split('?')[1],
no = param.split('=')[1],
currSpan, 
currCls,
ntcTemplateSrc = $('#ntc-list').html();

var ntcGenerator = Handlebars.compile(ntcTemplateSrc);
var ntcNo,
memNo;

$('#ntc-board').attr("href", "/studyboot/html/mystudy/board.html?no=" + no);

function loadList(no) {
  $.getJSON('../../app/json/MyStudy/studyNtc?no=' + no,
      function(obj){

    $(ntcGenerator(obj)).appendTo('#ntc-board-list');

    // 공지사항 클릭시 디테일 부분으로 넘어가기
    $('.ntcList-btn').click((e) => {
      e.preventDefault();
      ntcNo = $(e.target).attr('data-no');
      memNo = $(e.target).attr('data-mem');
      $("#contents").load("/studyboot/html/mystudy/view.html", function(e) {
        $('#ntc-no').attr('data-no', ntcNo);
        $('#mem-no').attr('data-mem', memNo);
      });
      
    });

  });
};

loadList(no);




$(document).on('ready', function () {

  // initialization of tabs
  $.HSCore.components.HSTabs.init('[role="tablist"]');

  // initialization of rating
  $.HSCore.components.HSRating.init($('.js-rating'), {
    spacing: 2
  });

  // initialization of HSMegaMenu component
  $('.js-mega-menu').HSMegaMenu({
    event: 'hover',
    pageContainer: $('.container'),
    breakpoint: 991
  });

  // initialization of horizontal progress bars
  setTimeout(function() { // important in this case
    var horizontalProgressBars = $.HSCore.components.HSProgressBar.init('.js-hr-progress-bar', {
      direction: 'horizontal',
      indicatorSelector: '.js-hr-progress-bar-indicator'
    });
  }, 1);
});











