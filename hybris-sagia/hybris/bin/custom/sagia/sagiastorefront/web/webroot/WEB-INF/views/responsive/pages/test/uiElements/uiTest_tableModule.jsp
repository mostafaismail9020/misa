<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">tableModule</h1>
	<p class="uiTest-description">Different Tables available</p>
</div>
<!-- End of Module description-->

<div class="tableModule">
  <table class="tableModule-table">
	<thead class="tableModule-head">
	  <tr>
		<th>Last update</th>
		<th>Ticket Number</th>
		<th>Enquiry Type</th>
		<th>Status</th>
		<th>Options</th>
	  </tr>
	</thead>
	<tbody class="tableModule-body">
	  <tr>
		<td>08 Jan 2018</td>
		<td>
			<div class="tableModule-headline">Headline just testing</div>
			<div class="tableModule-subHeadline">Lorem service</div>
			<div class="tableModule-description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Praesentium tempore, </div>
		</td>
		<td>Enquiry</td>
		<td>Closed</td>
		<td>Details</td>
	  </tr>
	  <tr>
		<td>08 Jan 2018</td>
		<td>10045448</td>
		<td>Enquiry</td>
		<td>Open</td>
		<td>Details</td>
	  </tr>
	  <tr>
		<td>08 Jan 2018</td>
		<td>10045448</td>
		<td>Enquiry</td>
		<td>Open</td>
		<td>Details</td>
	  </tr>  
	</tbody>
  </table>
</div>

tableModule_striped
<div class="tableModule tableModule_striped">
  <table class="tableModule-table">
	<thead class="tableModule-head">
	  <tr>
		<th>Last update</th>
		<th>Ticket Number</th>
		<th>Enquiry Type</th>
		<th>Status</th>
		<th>Options</th>
	  </tr>
	</thead>
	<tbody class="tableModule-body">
	  <tr>
		<td>08 Jan 2018</td>
		<td>10045448</td>
		<td>Enquiry</td>
		<td>Closed</td>
		<td>Details</td>
	  </tr>
	  <tr>
		<td>08 Jan 2018</td>
		<td>10045448</td>
		<td>Enquiry</td>
		<td>Open</td>
		<td>Details</td>
	  </tr>
	  <tr>
		<td>08 Jan 2018</td>
		<td>10045448</td>
		<td>Enquiry</td>
		<td>Open</td>
		<td>Details</td>
	  </tr>  
	</tbody>
  </table>
</div>

tableModule_slim
<div class="tableModule tableModule_slim">
  <table class="tableModule-table">
	<thead class="tableModule-head">
	  <tr>
		<th>Last update</th>
		<th>Ticket Number</th>
		<th>Enquiry Type</th>
		<th>Status</th>
		<th>Options</th>
	  </tr>
	</thead>
	<tbody class="tableModule-body">
	  <tr>
		<td>08 Jan 2018</td>
		<td>10045448</td>
		<td>Enquiry</td>
		<td>Closed</td>
		<td>Details</td>
	  </tr>
	  <tr>
		<td>08 Jan 2018</td>
		<td>10045448</td>
		<td>Enquiry</td>
		<td>Open</td>
		<td>Details</td>
	  </tr>
	  <tr>
		<td>08 Jan 2018</td>
		<td>10045448</td>
		<td>Enquiry</td>
		<td>Open</td>
		<td>Details</td>
	  </tr>  
	</tbody>
  </table>
</div>
         
              
                   
tableModule_footer
<div class="tableModule tableModule_footer">
  <table class="tableModule-table">
	<tbody class="tableModule-body">
	  <tr>
		<td>License Fee</td>
		<td>1234567,00 SAR</td>
	  </tr>
	  <tr>
		<td>Tax</td>
		<td>123,45 SAR</td>
	  </tr> 
	</tbody>
	<tfoot class="tableModule-footer">
	  <tr>
		<td>Total</td>
		<td>3333333,45 SAR</td>
	  </tr>		
	</tfoot>
  </table>
</div>
         

tableModule_businessActivities  
<div class="tableModule tableModule_businessActivities">
  <table class="tableModule-table">
	<tbody class="tableModule-body">
		<tr>
			<td>Section:</td>
			<td>
				<p>C - Manufacturing
					<button type="" class="btn btn_link iconElement iconElement_edit02">
						<icon:edit/>
					</button>
				</p>
			</td>
		</tr>
		<tr>
			<td>Division:</td>
			<td>
				<p>
					26 - Manufacture of computer, electronic and optical products
					<button type="" class="btn btn_link iconElement iconElement_edit02">
						<icon:edit/>
					</button>
				</p>
			</td>
		</tr>
		<tr>
			<td>Group:</td>
			<td>
				<p>
					265 - Manufacture of measuring, testing, navigating and control equipment; watches and clocks
					<button type="" class="btn btn_link iconElement iconElement_edit02">
						<icon:edit/>
					</button>
				</p>
			</td>
		</tr>
		<tr>
			<td>Class:</td>
			<td>
				<p>
					2651 - Manufacture of measuring, testing, navigating and control equipment
					<button type="" class="btn btn_link iconElement iconElement_edit02">
						<icon:edit/>
					</button>
				</p>
			</td>
		</tr>	  	   
	</tbody>
  </table> 
</div>
         
fixedWidth (25%)
<div class="tableModule tableModule_fixedWidth">
  <table class="tableModule-table">
	<thead class="tableModule-head">
	  <tr>
		<th>Name</th>
		<th>Mobile number</th>
		<th>Email</th>
		<th>National ID</th>
	  </tr>
	</thead>
	<tbody class="tableModule-body">
	  <tr>
		<td>Ilam Siva</td>
		<td>+966 (23324)</td>
		<td>v.j@sa.com</td>
		<td>402986</td>
	  </tr>
	  <tr>
		<td>Ronald Siva</td>
		<td>+966 (23324)</td>
		<td>ro.j@sa.com</td>
		<td>1402986</td>
	  </tr>
	  <tr>
		<td>Donald Siva</td>
		<td>+966 (23324)</td>
		<td>Dov.j@sa.com</td>
		<td>2402986</td>
	  </tr>	  
	</tbody>
  </table>
</div>
          
    
</div>

