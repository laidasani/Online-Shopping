$(function() {
	
	// solving the active menu problem
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;	
	case 'All Products':
		$('#listProducts').addClass('active');
		break;	
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	default:
		if(menu=='Home')
			break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
	}
	
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if((token!=undefined && header !=undefined) && (token.length > 0 && header.length > 0)) {		
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
	
	//code for jquery dataTable
	var $table=$('#productListTable');
	
	
	if($table.length){
		
		var jsonUrl='';
		if(window.categoryId==''){
			jsonUrl=window.contextRoot +'/json/data/all/products';
		}
		else
		{
			jsonUrl=window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
		}
			
		$table.DataTable({
			
			lengthMenu: [[3,5,10,-1],['3 Records','5 Records','10 Records','ALL Records',]],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data:'code',
					mRender: function(data,type,row){
						
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{
					data:'name'
				},
				{
					data:'brand'
				},
				{
					data:'unitPrice',
					mRender: function(data,type,row) {
						return '&#8377; '+ data
					}
				},
				{
					data:'quantity',
					mRender: function(data,type,row){
						if(data<1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						else{
							return data;
						}
					}
						
				},
				{
					data:'id',
					bSortable:false,
					mRender: function(data,type,row) {
						var str = '';
						str += '<a href="'+window.contextRoot+ '/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160';
						
						if(userRole=='ADMIN'){
							str += '<a href="'+window.contextRoot+ '/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
						}
						else
						{
						
							if(row.quantity<1){
								str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}
							else{
									str += '<a href="'+window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
								
							}
						}
						return str;
					}
				}
	
			]
		})
	}
	
	//dismissing alert after 3 seconds
	var $alert=$('.alert');
	if($alert.length){
		setTimeout(function() {
			$alert.fadeOut('slow');
		},3000)
	}
	
	
	//--------------------
	
	
	
	
	//DataTable for admin
	var $adminProductsTable=$('#adminProductsTable');
	
	
	if($adminProductsTable.length){
		
		var jsonUrl=window.contextRoot +'/json/data/admin/all/products';
	
			
		$adminProductsTable.DataTable({
			
			lengthMenu: [[10,30,50,-1],['10 Records','30 Records','50 Records','ALL']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'id'
				},
				{
					data:'code',
					mRender: function(data,type,row){
						
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
					}
				},
				{
					data:'name'
				},
				{
					data:'brand'
				},
				{
					data:'quantity',
					mRender: function(data,type,row){
						if(data<1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						else{
							return data;
						}
					}
						
				},
				{
					data:'unitPrice',
					mRender: function(data,type,row) {
						return '&#8377; '+ data
					}
				},
				{
					data:'active',
					bSortable: false,
					mRender: function(data,type,row){
						var str='';
						str+='<label class="switch">';
						if(data)
							str+='<input type="checkbox" checked="checked" value="'+row.id+'" />';
						else
							str+='<input type="checkbox"value="'+row.id+'" />';
						str+='<div class="slider"></div></label>';
						return str;
						
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender(data,type,row){
						var str='';
						str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
						str+='<span class="glyphicon glyphicon-pencil"></span></a>';
								return str;
					}
					
				}

	
			],
			initComplete: function() {
				var api=this.api();
				api.$('.switch input[type="checkbox"]').on('change', function() {
					
					var checkbox=$(this);
					var checked=checkbox.prop('checked');
					var dMsg=(checked)?'You want to activate the product?':
						'You want to deactivate the product?';
					var value=checkbox.prop('value');
					
					
					bootbox.confirm({
						size:'medium',
						title:'Product Activation & Deactivation',
						message: dMsg,
						callback: function(confirmed) {
							
							if(confirmed) {
								
								console.log(value);
								
								var activationUrl=window.contextRoot+'/manage/product/' +value+'/activation';
								$.post(activationUrl,function(data){
									bootbox.alert({
										size: 'medium',
										title: 'Information',
										message: data
									});
								});
								
							}
								else{
									checkbox.prop('checked',!checked);
								}
							
						}
					});
					
					
				});
			}
		})
	}
	
	
	//----------------
	// validating the category form element	
	$categoryForm = $('#categoryForm');
	
	if($categoryForm.length) {
		
		$categoryForm.validate({			
				rules: {
					name: {
						required: true,
						minlength: 2
					},
					description: {
						required: true				
					}				
				},
				messages: {					
					name: {
						required: 'Please enter category name!',
						minlength: 'Please enter atleast two characters'
					},
					description: {
						required: 'Please enter a description for the category!'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					//add class of help-block
					error.addClass('help-block');
					
					//add the error element after the input element
					error.insertAfter(element);
				}				
			}
		
		);
		
	}
	
$loginForm = $('#loginForm');
	
	if($loginForm.length) {
		
		$loginForm.validate({			
				rules: {
					username: {
						required: true,
						email: true
						
					},
					password: {
						required: true
					}				
				},
				messages: {					
					username: {
						required: 'Please enter your email!',
						email: 'Please enter a valid email address!'
					},
					password: {
						required: 'Please enter your password!'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					// Add the 'help-block' class to the error element
					error.addClass("help-block");
					
					// add the error label after the input element
					error.insertAfter(element);
				}				
			}
		
		);
		
	}
	
	//--------------
	/* handle refresh cart*/	
	$('button[name="refreshCart"]').click(function(){
		var cartLineId = $(this).attr('value');
		var countElement = $('#count_' + cartLineId);
		var originalCount = countElement.attr('value');
		var currentCount=countElement.val();
		// do the checking only the count has changed
		if(currentCount !== originalCount) {	
			
			// check if the quantity is within the specified range
			if(currentCount < 1 || currentCount > 3) {
				// set the field back to the original field
				
				countElement.val(originalCount);
				bootbox.alert({
					size: 'medium',
			    	title: 'Error',
			    	message: 'Product Count should be minimum 1 and maximum 3!'
				});
			}
			else {
				// use the window.location.href property to send the request to the server
				var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
				window.location.href = updateUrl;
			}
		}
	});			
	
	
	//--------------
});