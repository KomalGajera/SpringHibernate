(function($) {

	var abc;
	
	$.ajax({url: "displaycountry",type:'GET',
        success: function(list){      	        	
            var select = $('#country');           
              $.each(list, function(index, value) {
              $('<option>').val(value['countryName']).text(value['countryName']).appendTo(select);
          });
        },
        error: function(data) {
            alert('there is not find country name!');
        } 
	});
	
	var id = getUrlVars()["id"];	
	if(id!=undefined)
		{	
		
		$.ajax({url: "checkaddress",type:'POST',data:'id='+id, 			
 	        success: function(list){   
 	        	alert(list);
 	        	$('#example1').repeater({
 	        	    btnAddClass: 'r-btnAdd',
 	        	    btnRemoveClass: 'r-btnRemove',
 	        	    groupClass: 'r-group',
 	        	    minItems: list,
 	        	    maxItems: 0,
 	        	    startingIndex: 0,
 	        	    showMinItemsOnLoad: true,
 	        	    reindexOnDelete: true,
 	        	    repeatMode: 'append',
 	        	    animation: 'fade',
 	        	    animationSpeed: 400,
 	        	    animationEasing: 'swing',
 	        	    clearValues: true
 	        	});
 	        		 	         
 	        },
 	        error: function(data) {
 	            alert('there is an error while fetch user address..');
 	        } 
 		});
		
		
		$.ajax({url: "userbyid",
			type:'POST',
			data:"id="+id,
	        success: function(data){   
	        	var select = $('#state'); 
	        	var abc=JSON.stringify(data);
            	var value = JSON.parse(abc);
            	var i=0;
            	$("#example1 .r-group").last().remove();
            	$('#id').val(id);
            	$('#fname').val(value.fname);
            	$('#lname').val(value.lname);
            	$('#email').val(value.email);
            	$('h2').text("Updation Form");
            	$('#contact_no').val(value.number);
            	$('#psw').val(value.password);
            	$('#psw_confirm').val(value.password);
            	$('#birthdate').val(value.dob);
            	$('#country').val(value.country);
            	$('<option selected="selected">').val(value.state).text(value.state).appendTo(select);
            	$('#profileimg').attr('src',"image/"+value.userId+"");
            	if(value.gender=='female')
            	{
            		$("#female").attr('checked', 'checked');
            	}
            	if(value.gender=='male')
            	{
            		$("#male").attr('checked', 'checked');
            	}
            	var element=value.hobby.split(' ');
            	$.each(element,function(index,hobby){
            		$('input[type=checkbox]').filter(function(){
            			   return this.value === hobby;
            			}).prop('checked', true);
            	});
            	$.each(value.add, function (key, value) {            		
            		var a="#address_"+i+"_name";
            		$(a).val(value);   
            		i++;                	
                });
            	
            	
            	
	        },
	        error: function(data) {
	            alert('User id is not valid please check the id.');
	        } 
		});
	}
	else{
		
	
	}
	
    $.fn.repeater = function(options, data) {
        var $container = $(this),
            $btnAdd, $btnRemove, patternName, patternId, patternText,
            idVal, nameVal, labelText, labelFor, $elem, elemName,
            $label, row, $newClone, $formElems;

        $container.opts = $.extend({}, $.fn.repeater.defaults, options);
        $container.repeatCount = 0;

        $btnAdd = $container.find('.' + $container.opts.btnAddClass);
        if (!$btnAdd.length) {
            alert('You must specify a valid jQuery selector for the add button option in Form Repeater.');
            return false;
        }

        // parse out group details
        $container.group = $('.' + $container.opts.groupClass);
        if (!$container.group.length) {
            alert('You must specify a valid jQuery selector for the form element grouping option in Form Repeater.');
            return false;
        }

        // ensure the remove button exists
        $btnRemove = $container.group.find('.' + $container.opts.btnRemoveClass);
        if (!$btnRemove.length) {
            $btnRemove = $('<button type="button" name="rBtnRemove" class="' + $container.opts.btnRemoveClass + '" style="display:none" />')
            $btnRemove.appendTo($container);
        } else {
            // default hidden
//           $btnRemove.hide();
        }

        // narrow the group down to the first copy
        $container.group = $container.group.eq(0);
        // retrieve form elements
        $container.groupClone = $container.group.clone();
        // remove the initial item when minItems === 0
        if ($container.opts.minItems === 0) {
            // remove the group that describes the repeat
            $container.group.remove();
            // adjust the count to remove first item
            $container.repeatCount--;
        }

        // watch for remove
	$container.on('click', '.' + $container.opts.btnRemoveClass, $container, removeRepeater);
        // watch for add
	$container.on('click', '.' + $container.opts.btnAddClass, $container, addRepeater);

        // allows for initial population of form data
        if (data && data.length) {
            // create grouping for every row of data
            data.forEach(function(condition, row) {
                // keep cloning
                $newClone = $container.groupClone.clone();
                $newClone = _reindex($newClone, row, $container);

                if ($.isFunction($container.opts.beforeAdd)) {
                    $newClone = $container.opts.beforeAdd.call(this, $newClone);
                }

                $formElems = $newClone.find(':input');

                if ($formElems.length) {
                    // populate each input field
                    $formElems.each(function() {
                        $elem = $(this),
                            elemName = $elem.attr('name');

                        // check for matching value
                        if (typeof data[row][elemName] != 'undefined') {
                            if ($elem.is('input[type="checkbox"]')) {
                                if (data[row][elemName] === '' || data[row][elemName] === '1') {
                                    $elem.attr('checked', true);
                                }
                            } else {
                                $elem.val(data[row][elemName]);
                            }
                        } else {
                            if ($elem.is('input[type="checkbox"]')) {
                                $elem.attr('checked', false);
                            } else {
                                $elem.val('');
                            }
                        }

                        patternName = $elem.data('pattern-name');
                        if (patternName) {
                            nameVal = $elem.attr('name');
                            nameVal = parsePattern(patternName, nameVal, row, $container);
                            $elem.attr('name', nameVal);
                        }

                        patternId = $elem.data('pattern-id');
                        if (patternId) {
                            idVal = $elem.attr('id');
                            idVal = parsePattern(patternId, idVal, row, $container);
                            $elem.attr('id', idVal);
                        }

                        $label = $newClone.find('label[for=' + $elem.attr('id') + ']');
                        if (!$label.length) $label = $elem.parent('label');
                        if (!$label.length) $label = $elem.siblings('label');
                        if ($label.length) {
                            // ensure we have one copy
                            $label = $label.eq(0);
                            // update label text
                            patternText = $label.data('pattern-text');
                            labelText = $label.html();
                            if (labelText) {
                                labelText = parsePattern(patternText, labelText, row, $container);
                                $label.html(labelText);
                            }
                            // update label attribute
                            labelFor = $label.attr('for');
                            if (labelFor && idVal) {
                                $label.attr('for', idVal);
                            }
                        }
                    });

                }

                // append repeater to container
                if ($container.opts.repeatMode == 'append') {
                    $newClone.appendTo($container);
                } else if ($container.opts.repeatMode == 'prepend') {
                    $newClone.prependTo($container);
                } else if ($container.opts.repeatMode == 'insertAfterLast') {
                    $newClone.insertAfter($container.find('.' + $container.opts.groupClass).last());
                }

                // remove the initial dom container
                if ($container.group) {
                    $container.group.remove();
                    $container.group = null;
                }

                // calculate the repeatCount based on whats in the dom
                $container.repeatCount = $container.find('.' + $container.opts.groupClass).length - 1;
                $('#length').val($container.repeatCount);
                // shows removal buttons only inside the new clone when were above the minItems count
                if ($container.repeatCount > $container.opts.minItems - 1) {
                    $newClone.find('.' + $container.opts.btnRemoveClass).show();
                }
              
               

                if ($.isFunction($container.opts.afterAdd)) {
                    $container.opts.afterAdd.call(this, $newClone);
                }
            });
        }

        // ensure the $container is repeated for atleast the min-items amount
        if ($container.opts.showMinItemsOnLoad === true && $container.repeatCount < $container.opts.minItems - 1) {
            while ($container.repeatCount < $container.opts.minItems - 1) {
                $('.' + $container.opts.btnAddClass, $container).trigger('click');
            }
        }


        // daisy chain
        return this;
    }

    /**
     * Add a new repeater.
     */
    function addRepeater(data) {
        var container = data.data,
            tmpCount = container.repeatCount + 1,
            $doppleganger = container.groupClone.clone();
        if ($.isFunction(container.opts.beforeAdd)) {
            $doppleganger = container.opts.beforeAdd.call(this, $doppleganger);
        }

        // don't exceed the max allowable items
        if (container.opts.maxItems > 0 && container.repeatCount == container.opts.maxItems) {
            alert('You have hit the maximum allowable items.');
            return false;
        }

        _reindex($doppleganger, tmpCount, container);

        // shows removal buttons only inside the new clone when were above the minItems count
        if (container.repeatCount >= container.opts.minItems - 1) {
            $doppleganger.find('.' + container.opts.btnRemoveClass).show();
        }

        // append repeater to container
        if (container.opts.repeatMode == 'append') {
            $doppleganger.appendTo(container);
        } else if (container.opts.repeatMode == 'prepend') {
            $doppleganger.prependTo(container);
        } else if (container.opts.repeatMode == 'insertAfterLast') {
            $doppleganger.insertAfter(container.find('.' + container.opts.groupClass).last());
        }
      
        container.repeatCount++;
        $('#length').val(container.repeatCount);
        if ($.isFunction(container.opts.afterAdd)) {
            container.opts.afterAdd.call(this, $doppleganger);
        }
        return false;
    }

    /**
     * Remove a repeater.
     */
    function removeRepeater(data) {
        var $btn = $(this),
            container = data.data,
            $repeaters = container.find('.' + container.opts.groupClass),
            numRepeaters = $repeaters.length,
            $match;          
        if (numRepeaters <= container.opts.minItems) {
            return false;
        }
        // check if removing a specific repeater instance
        $match = $btn.closest('.' + container.opts.groupClass);
        if (!$match.length) {
            // determine if removing first or last repeater
            if (container.opts.repeatMode == 'append') {
                var $match = $repeaters.filter(':last');
            } else if (container.opts.repeatMode == 'prepend') {
                var $match = $repeaters.filter(':first');
            } else if (container.opts.repeatMode == 'insertAfterLast') {
                var $match = $repeaters.filter(':last');
            }
        }
        // ensure we have a match
        if ($match.length) {
            // remove the repeater
            if (container.opts.animation) {
                if (container.opts.animation == 'slide') {
                    $match.slideUp(container.opts.animationSpeed, container.opts.animationEasing, function() {
                        _remove($match, container);
                    });
                } else if (container.opts.animation == 'fade') {
                    $match.fadeOut(container.opts.animationSpeed, container.opts.animationEasing, function() {
                        _remove($match, container);
                    });
                } else if (typeof container.opts.animation == 'object') {
                    $match.animate(container.opts.animation, container.opts.animationSpeed, container.opts.animationEasing, function() {
                        _remove($match, container);
                    });
                }
            } else {
                _remove($match, container);
            }
        }
        return false;
    }
    /**
     * Parse the pattern.
     */
    function parsePattern(pattern, replaceText, count, container) {
        var returnVal = replaceText;
        count = parseInt(count);
        if (pattern) {
            // check pattern type
            if (pattern.indexOf('+=') > -1) {
                var matches = pattern.match(/\+=(\d+)/i);
                if (matches && matches.length && matches[1]) {
                    var incr = parseInt(matches[1]);
                    returnVal = pattern.replace(/\+=(\d)+/i, container.opts.startingIndex + count + incr);
                }
            }
            if (pattern.indexOf('++') > -1) {
                returnVal = pattern.replace(/\+\+/gi, container.opts.startingIndex + count);
            }
        }
        return returnVal;
    }
    /**
     * Wrapper to handle re-indexing form elements in a group.
     */
    function reindex(container) {
        var $repeaters = container.find('.' + container.opts.groupClass),
            startIndex = container.opts.startingIndex,
            $curGroup;

        $repeaters.each(function() {
            $curGroup = $(this);
            _reindex($curGroup, startIndex, container);
            startIndex++;
        });
    }
    /**
     * Remove a match and reindex.
     */
    function _remove($match, container) {
        if ($.isFunction(container.opts.beforeDelete)) {
            container.opts.beforeDelete.call(this, $match);
        }
        $match.remove();

        if (typeof container.repeatCount === "number") {
            container.repeatCount--;
            $('#length').val(container.repeatCount);
        }

        if (container.opts.reindexOnDelete) {
            reindex(container);
        }

        if ($.isFunction(container.opts.afterDelete)) {
            container.opts.afterDelete.call(this, $match);
        }
    }

    /**
     * Handle reindexing each form element in a group.
     */
    function _reindex($curGroup, index, container) {
        var $formElems = $curGroup.find(':input'),
            patternName, patternId, patternText,
            idVal, nameVal, $label, labelText, labelFor,
            $elem;

        if ($formElems.length) {
            $formElems.each(function() {
                $elem = $(this);

                patternName = $elem.data('pattern-name');
                if (patternName) {
                    nameVal = $elem.attr('name');
                    nameVal = parsePattern(patternName, nameVal, index, container);
                    $elem.attr('name', nameVal);

                    if ($elem.is('input[type="checkbox"]') || $elem.is('input[type="radio"]')) {
                        if ($elem.prop('checked')) {
                            $elem.attr('checked', true);
                        } else {
                            $elem.attr('checked', false);
                        }
                    }
                }

                patternId = $elem.data('pattern-id');
                if (patternId) {
                    idVal = $elem.attr('id');
                    idVal = parsePattern(patternId, idVal, index, container);
                    $elem.attr('id', idVal);
                }

                $label = $curGroup.find('label[for=' + $elem.attr('id') + ']');
                if (!$label.length) $label = $elem.parent('label');
                if (!$label.length) $label = $elem.siblings('label');
                if ($label.length) {
                    // ensure we have one copy
                    $label = $label.eq(0);
                    // update label text
                    patternText = $label.data('pattern-text');
                    labelText = $label.html();
                    if (labelText) {
                        labelText = parsePattern(patternText, labelText, index, container);
                        $label.html(labelText);
                    }
                    // update label attribute
                    labelFor = $label.attr('for');
                    if (labelFor && idVal) {
                        $label.attr('for', idVal);
                    }
                }
            });
        }

        return $curGroup;
    }

	
	
	'use strict';
	var table = $("#example");
	
	$("#email").blur(function(){			
		 	var email=$( "#email" ).val();   
		 	alert(email);
 			$.ajax({url: "checkemail",type:'POST',data:'email='+email, 			
 	        success: function(list){      
 	        	if(list==1){
 	        		$(".error").html("email already exits"); 	        		
 	        	}else{
 	        		$(".error").html("");
 	        	}	 	         
 	        },
 	        error: function(data) {
 	            alert('woops!');
 	        } 
 		});
	});	
	

	  $("#country").change(function(){
	    	 var country_name=$( "#country option:selected" ).text();    	 
	    		$.ajax({url: "displaystatebycountry/"+country_name,type:'POST',
	    			
	    	        success: function(list){      	        	
	    	            var select = $('#state');
	    	             select.find('option').remove();
	    	              $.each(list, function(index, value) {
	    	              $('<option>').val(value['stateName']).text(value['stateName']).appendTo(select);
	    	          });
	    	        },
	    	        error: function(data) {
	    	            alert('state name is not found!!!');
	    	        } 
	    		});
	    	
	    });
	/*
	 * ================================================================== [
	 * Daterangepicker ]
	 */
	$("#profile").change(function() {
		readURL(this);
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#profileimg').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	try {
		$('.js-datepicker').daterangepicker({
			"singleDatePicker" : true,
			"showDropdowns" : true,
			"autoUpdateInput" : false,
			locale : {
				format : 'DD/MM/YYYY'
			},
		});

		var myCalendar = $('.js-datepicker');
		var isClick = 0;

		$(window).on('click', function() {
			isClick = 0;
		});

		$(myCalendar).on('apply.daterangepicker', function(ev, picker) {
			isClick = 0;
			$(this).val(picker.startDate.format('DD/MM/YYYY'));

		});

		$('.js-btn-calendar').on('click', function(e) {
			e.stopPropagation();

			if (isClick === 1)
				isClick = 0;
			else if (isClick === 0)
				isClick = 1;

			if (isClick === 1) {
				myCalendar.focus();
			}
		});

		$(myCalendar).on('click', function(e) {
			e.stopPropagation();
			isClick = 1;
		});

		$('.daterangepicker').on('click', function(e) {
			e.stopPropagation();
		});

	} catch (er) {
		console.log(er);
	}
	/*
	 * [ Select 2 Config ]
	 * ===========================================================
	 */

	try {
		var selectSimple = $('.js-select-simple');

		selectSimple.each(function() {
			var that = $(this);
			var selectBox = that.find('select');
			var selectDropdown = that.find('.select-dropdown');
			selectBox.select2({
				dropdownParent : selectDropdown
			});
		});

	} catch (err) {
		console.log(err);
	}

})(jQuery);



function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
var id = getUrlVars()["id"];	

$('#example1').repeater({
	    btnAddClass: 'r-btnAdd',
	    btnRemoveClass: 'r-btnRemove',
	    groupClass: 'r-group',
	    minItems: 1,
	    maxItems: 0,
	    startingIndex: 0,
	    showMinItemsOnLoad: true,
	    reindexOnDelete: true,
	    repeatMode: 'append',
	    animation: 'fade',
	    animationSpeed: 400,
	    animationEasing: 'swing',
	    clearValues: true
	});


$.fn.repeater.defaults = {
    groupClass: 'r-group',
    btnAddClass: 'r-btnAdd',
    btnRemoveClass: 'r-btnRemove',
    minItems: 1,
    maxItems: 0,
    startingIndex: 0,
    reindexOnDelete: true,
    showMinItemsOnLoad: false,
    repeatMode: 'insertAfterLast', // append, prepend, insertAfterLast
    animation: null,
    animationSpeed: 400,
    animationEasing: 'swing',
    clearValues: true,
    beforeAdd: function($doppleganger) {
        return $doppleganger;
    },
    afterAdd: function($doppleganger) {},
    beforeDelete: function($elem) {},
    afterDelete: function() {}
};
