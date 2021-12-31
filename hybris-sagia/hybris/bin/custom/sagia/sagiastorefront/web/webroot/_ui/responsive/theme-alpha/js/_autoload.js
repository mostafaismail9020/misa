//################################################################
//#### Autoload
//################################################################
// 
// ACC.sample={
// 	_autoload: [
// 		"samplefunction",
// 		["somefunction", "some expression to test"]
// 		["somefunction","some expression to test","elsefunction"]
// 	],

// 	samplefunction:function(){
// 		//... do some suff here, executed every time ...
// 	},

// 	somefunction:function(){
// 		//... do some suff here. if expression match ...
// 	},

// 	elsefunction:function(){
// 		//... do some suff here. if expression NOT match ...
// 	}

// }

//  // sample expression: $(".js-storefinder-map").length != 0


function _autoloadSagia(){
	$.each(SAGIA,function(section,obj){
		if($.isArray(obj._autoload)){
			$.each(obj._autoload,function(key,value){
				if($.isArray(value)){
					if(eval(value[1]) === true){
						SAGIA[section][value[0]]();
					}else{
						if(eval(value[2]) === true){
							SAGIA[section][value[2]]()
						}
					}
				}else{
					SAGIA[section][value]();
				}
			})
		}
	});
}




// 	_enquireload: [
// 		[(screenSmMin | screenMdMin | screenLgMin | screenXsMax | screenSmMax | screenMdMax),[match, unmatch, setup, deferSetup, destroy] ]
// 	],



function _enquireload(){
	var enquiredefault=[
		null,
		null,
		null,
		null,
		true,
		null
	];
	$.each(SAGIA,function(section,obj){
		if($.isArray(obj._enquireload)){
			$.each(obj._enquireload,function(key,value){
				value = enquiredefault.map(function(e,i){
					return (value[i])? value[i]: e;
				});
				var mediaquery = null;

				switch(value[0]){
					case "screenSmMin":
						mediaquery = "(min-width: "+screenSmMin+")";	
					break;
					case "screenMdMin":
						mediaquery = "(min-width: 767px)";	
					break;
					case "screenLgMin":
						mediaquery = "(min-width: "+screenLgMin+")";	
					break;
					case "screenXsMax":
//						mediaquery = "(max-width: "+screenXsMax+")";
						mediaquery = "(max-width: 575px)";	
					break;
					case "screenSmMax":
						mediaquery = "(max-width: 766px)";	
					break;
					case "screenMdMax":
//						mediaquery = "(max-width: "+screenMdMax+")";	
						mediaquery = "(max-width: 991px)";	
					break;
				}

				if(mediaquery){
					enquire.register(mediaquery, {
						match : SAGIA[section][value[1]],      
						unmatch : SAGIA[section][value[2]],    
						setup : SAGIA[section][value[3]],    
						deferSetup : true,
						destroy : SAGIA[section][value[4]]
					});
				}
			})
		}
	})
}

$(function(){
	_autoloadSagia();
	_enquireload();
});