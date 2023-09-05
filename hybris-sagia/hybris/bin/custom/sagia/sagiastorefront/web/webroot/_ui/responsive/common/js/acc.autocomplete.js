ACC.autocomplete = {

	_autoload: [
		"bindSearchAutocomplete",
        "bindDisableSearch"
	],

	bindSearchAutocomplete: function ()
	{
		// extend the default autocomplete widget, to solve issue on multiple instances of the searchbox component
		$.widget( "custom.yautocomplete", $.ui.autocomplete, {
			_create:function(){
				
				// get instance specific options form the html data attr
				var option = this.element.data("options");
				// set the options to the widget
				this._setOptions({
					minLength: option.minCharactersBeforeRequest,
					displayProductImages: option.displayProductImages,
					delay: option.waitTimeBeforeRequest,
					autocompleteUrl: option.autocompleteUrl,
					source: this.source,
					autocompleteUrlClick: option.autocompleteUrlClick,
					autocompleteUrlParam: option.autocompleteUrlParam
				});
				
				// call the _super()
				$.ui.autocomplete.prototype._create.call(this);
				
			},
			options:{
				cache:{}, // init cache per instance
				focus: function (){return false;}, // prevent textfield value replacement on item focus
				select: function (event, ui){
					ui.item.value = ACC.sanitizer.sanitize(ui.item.value, false);
                    window.location.href = ui.item.url;
                }
			},
			_renderItem : function (ul, item){
				
				if (item.type == "autoSuggestion"){
					var renderHtml = "<a href='"+ item.url + "' ><div class='name'>" + item.value + "</div></a>";
					return $("<li>")
							.data("item.autocomplete", item)
							.append(renderHtml)
							.appendTo(ul);
				}
				else if (item.type == "productResult"){

					var renderHtml = "<a href='" + item.url + "' >";

					if (item.image != null){
						renderHtml += "<div class='thumb'><img src='" + item.image + "'  /></div>";
					}

					renderHtml += 	"<div class='name'>" + item.value +"</div>";
					renderHtml += 	"<div class='price'>" + item.price +"</div>";
					renderHtml += 	"</a>";

					return $("<li>").data("item.autocomplete", item).append(renderHtml).appendTo(ul);
				}
			},
			source: function (request, response)
			{
				var self=this;
				var term = request.term.toLowerCase();
				if (term in self.options.cache)
				{
					return response(self.options.cache[term]);
				}

				$.getJSON(self.options.autocompleteUrl, {term: request.term}, function (data)
				{
					var autoSearchData = [];
					/*if(data.suggestions != null){
						$.each(data.suggestions, function (i, obj)
						{
						    //set url click
						    var url = self.options.autocompleteUrlClick
                                ? self.options.autocompleteUrlClick+"?"
                                : ACC.config.encodedContextPath + "/search?";
                            //set url param
						    url += self.options.autocompleteUrlParam
						        ? self.options.autocompleteUrlParam+"=" + obj.term
						        : "text=" + obj.term;
							autoSearchData.push({
								value: obj.term,
								url: url,
								type: "autoSuggestion"
							});
						});
					}*/
					if (data.opportunities != null) {
						if (data.opportunities.length > 0)
							autoSearchData.push({
								value: "<strong class='title'>Opportunities</strong>",
								code: null,
								desc: null,
								manufacturer: null,
								url:  window.location.href + "sectors-opportunities/opportunities",
								type: "productResult",
								image: null
							});
						$.each(data.opportunities, function (i, obj)
						{
							autoSearchData.push({
								value: "<span style='margin-top: -7px;'>" + ACC.sanitizer.sanitize(obj.name) + "</span>",
								code: obj.code,
								desc: ACC.sanitizer.sanitize(obj.description),
								manufacturer: ACC.sanitizer.sanitize(obj.manufacturer),
								url:  ACC.config.encodedContextPath + obj.url,
								// price: obj.price.formattedValue,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? obj.images[0].url : null // prevent errors if obj.images = null
							});
						});
					}
					if (data.news != null) {
						if (data.news.length > 0)
							autoSearchData.push({
								value: "<strong class='title'>News</strong>",
								code: null,
								desc: null,
								manufacturer: null,
								url:  window.location.href + "mediaCenter/news",
								type: "productResult",
								image: null
							});
						$.each(data.news, function (i, obj)
						{
							autoSearchData.push({
								value: ACC.sanitizer.sanitize(obj.name),
								code: obj.code,
								desc: ACC.sanitizer.sanitize(obj.description),
								manufacturer: ACC.sanitizer.sanitize(obj.manufacturer),
								url:  ACC.config.encodedContextPath + obj.url,
								// price: obj.price.formattedValue,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? obj.images[0].url : null // prevent errors if obj.images = null
							});
						});
					}
					if (data.events != null) {
						if (data.events.length > 0)
							autoSearchData.push({
								value: "<strong class='title'>Events</strong>",
								code: null,
								desc: null,
								manufacturer: null,
								url:  window.location.href + "mediaCenter/events",
								type: "productResult",
								image: null
							});
						$.each(data.events, function (i, obj)
						{
							autoSearchData.push({
								value: ACC.sanitizer.sanitize(obj.name),
								code: obj.code,
								desc: ACC.sanitizer.sanitize(obj.description),
								manufacturer: ACC.sanitizer.sanitize(obj.manufacturer),
								url:  ACC.config.encodedContextPath + obj.url,
								// price: obj.price.formattedValue,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? obj.images[0].url : null // prevent errors if obj.images = null
							});
						});
					}
					if (data.articles != null) {
						if (data.articles.length > 0)
							autoSearchData.push({
								value: "<strong class='title'>Articles</strong>",
								code: null,
								desc: null,
								manufacturer: null,
								url:  window.location.href + "mediaCenter/events",
								type: "productResult",
								image: null
							});
						$.each(data.articles, function (i, obj)
						{
							autoSearchData.push({
								value: ACC.sanitizer.sanitize(obj.name),
								code: obj.code,
								desc: ACC.sanitizer.sanitize(obj.description),
								manufacturer: ACC.sanitizer.sanitize(obj.manufacturer),
								url:  ACC.config.encodedContextPath + obj.url,
								// price: obj.price.formattedValue,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? obj.images[0].url : null // prevent errors if obj.images = null
							});
						});
					}
					if (data.products != null) {
						if (data.products.length > 0)
							autoSearchData.push({
								value: "<strong class='title'>Opportunities</strong>",
								code: null,
								desc: null,
								manufacturer: null,
								url:  "#",
								type: "productResult",
								image: null
							});
						$.each(data.products, function (i, obj)
						{
							autoSearchData.push({
								value: ACC.sanitizer.sanitize(obj.name),
								code: obj.code,
								desc: ACC.sanitizer.sanitize(obj.description),
								manufacturer: ACC.sanitizer.sanitize(obj.manufacturer),
								url:  ACC.config.encodedContextPath + obj.url,
								// price: obj.price.formattedValue,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? obj.images[0].url : null // prevent errors if obj.images = null
							});
						});
					}
					self.options.cache[term] = autoSearchData;
					return response(autoSearchData);
				});
			}

		});

	
		$search = $(".js-site-search-input");
		if($search.length>0){
			$search.yautocomplete()
		}

	},

	bindDisableSearch: function ()
    {
        $('#js-site-search-input').keyup(function(){
        	$('#js-site-search-input').val($('#js-site-search-input').val().replace(/^\s+/gm,''));
            $('.js_search_button').prop('disabled', this.value == "" ? true : false);
        })
    }
};