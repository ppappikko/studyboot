var div = $('div'),
		templateSrc = $('#div-template').html(), // script 태그에서 탬플릿 데이터를 꺼낸다.
    trGenerator = Handlebars.compile(templateSrc); // Handlebars를 통해 템플릿 데이터를 가지고 최종 결과를 생성할 함수를 준비한다.

// JSON 형식의 데이터 목록 가져오기
function loadList() {
  
  $.getJSON('../../app/json/space/list', 
    function(obj) {
      
      $(trGenerator(obj)).appendTo(div);
      

    }); // Bitcamp.getJSON()
  
} // loadList()

loadList();








