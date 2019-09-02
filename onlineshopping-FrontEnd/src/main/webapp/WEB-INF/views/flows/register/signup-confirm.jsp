<%@include file="../shared/flows-header.jsp" %>
			<div class="container">
				<div class="row">
	
		<div class="col-sm-6">
	
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4>Personal Details</h4>
				</div>
			
				<div class="panel-body">
					<div class="text-center">
						<h4>Name : <strong>${registerModel.user.firstName} ${registerModel.user.lastName}</strong></h4>
						<h5>Email : <strong>${registerModel.user.email}</strong></h5>
						<h5>Contact : <strong>${registerModel.user.contactNumber}</strong></h5>
						<h5>Role : <strong>${registerModel.user.role}</strong></h5>
						<p>
							<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>
			
			</div>
					
		
		</div>
		
		<div class="col-sm-6">
		
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4>Billing Address</h4>
				</div>
			
				<div class="panel-body">
					<div class="text-center">
						<h4>${registerModel.billing.addressLineOne}, </h4>
						<h4>${registerModel.billing.addressLineTwo}, </h4>
						<h4>${registerModel.billing.city} -  ${registerModel.billing.postalCode}, </h4>
						<h4>${registerModel.billing.state}</h4>
						<h4>${registerModel.billing.country}</h4>
						<p>
							<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>
			
			</div>
		
		</div>
	
	</div>
	
	<div class="row">
		
		<div class="col-sm-4 col-sm-offset-4">
			
			<div class="text-center">
				
				<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-lg btn-primary">Confirm</a>
				
			</div>
			
		</div>
		
	</div>

			
			</div>
		


<%@include file="../shared/flows-footer.jsp"%>