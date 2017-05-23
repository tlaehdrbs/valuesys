var ParallaxSlider = function () {

    return {
        
        //Parallax Slider
        initParallaxSlider: function () {
			$('#da-slider').cslider({
			    current     : 0,    
			    // index of current slide
			     
			    // bgincrement 50에서 200으로 수정함
			    bgincrement : 200,  
			    // increment the background position 
			    // (parallax effect) when sliding
			     
			    autoplay    : false,
			    // slideshow on / off
			     
			    interval    : 4000  
			    // time between transitions
			});
        },

    };

}();        