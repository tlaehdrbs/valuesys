var Datepicker = function () {

    return {
        
        //Datepickers
        initDatepicker: function () {
	        // Regular datepicker
	        $('#date').datepicker({
	            dateFormat: 'dd.mm.yy',
	            prevText: '<i class="fa fa-angle-left"></i>',
	            nextText: '<i class="fa fa-angle-right"></i>'
	        });
	        
	        // Date range
	        $('#start').datepicker({
	            // dateFormat 수정
	        	/*dateFormat: 'dd.mm.yy',*/
	        	showMonthAfterYear : true,
				maxDate : '-0',
				yearSuffix : '년',
				monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
						'8월', '9월', '10월', '11월', '12월' ],
				monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
						'7월', '8월', '9월', '10월', '11월', '12월' ],
				dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
	            onSelect: function( selectedDate )
	            {
	                $('#finish').datepicker('option', 'minDate', selectedDate);
	            }
	        });
	        $("#start").datepicker("option", "dateFormat", "yy-mm-dd"); //데이터 포맷으로 날짜의 반환 타입을 지정
			$("#start").datepicker("option", "showAnim", "slideDown"); //달력의 표시 형태
			
	        $('#finish').datepicker({
	        	// dateFormat 수정
	        	/*dateFormat: 'dd.mm.yy',*/
	        	showMonthAfterYear : true,
				maxDate : '-0',
				yearSuffix : '년',
				monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
						'8월', '9월', '10월', '11월', '12월' ],
				monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
						'7월', '8월', '9월', '10월', '11월', '12월' ],
				dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
	            onSelect: function( selectedDate )
	            {
	                $('#start').datepicker('option', 'maxDate', selectedDate);
	            }
	        });
	        $("#finish").datepicker("option", "dateFormat", "yy-mm-dd");
			$("#finish").datepicker("option", "showAnim", "slideDown");
	        
	        // Inline datepicker
	        $('#inline').datepicker({
	            dateFormat: 'dd.mm.yy',
	            prevText: '<i class="fa fa-angle-left"></i>',
	            nextText: '<i class="fa fa-angle-right"></i>'
	        });
	        
	        // Inline date range
	        $('#inline-start').datepicker({
	            dateFormat: 'dd.mm.yy',
	            prevText: '<i class="fa fa-angle-left"></i>',
	            nextText: '<i class="fa fa-angle-right"></i>',
	            onSelect: function( selectedDate )
	            {
	                $('#inline-finish').datepicker('option', 'minDate', selectedDate);
	            }
	        });
	        $('#inline-finish').datepicker({
	            dateFormat: 'dd.mm.yy',
	            prevText: '<i class="fa fa-angle-left"></i>',
	            nextText: '<i class="fa fa-angle-right"></i>',
	            onSelect: function( selectedDate )
	            {
	                $('#inline-start').datepicker('option', 'maxDate', selectedDate);
	            }
	        });
        }

    };
}();