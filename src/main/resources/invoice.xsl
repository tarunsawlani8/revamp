<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
<xsl:template match="invoice">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA5" page-height="12cm" page-width="10cm" margin-top="0.3cm" margin-bottom="0.3cm" margin-left="0.2cm" margin-right="0.2cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA5">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="12pt" font-weight="bold" space-after="1mm">Amar Opticals
          </fo:block>
		  <fo:block font-size="7pt" font-weight="italic" font-style="italic" space-after="1mm">Transport Nagar, Korba 495678, Ph: 7067979795
          </fo:block>
		   <fo:block font-size="8pt" >
		   Invoice Id: <xsl:value-of select="invoiceid"  />
Customer Name: <xsl:value-of select="name"  minOccur="0"/>
</fo:block>
<fo:block font-size="7pt">
Contact Number: <xsl:value-of select="contact" minOccur="0"/>
Referred By : <xsl:value-of select="referredby" minOccur="0"/>
Receipt Date: <xsl:value-of select="updatedate" minOccur="0"/>

          </fo:block>
          
          
      
          <fo:block font-size="7pt" margin-top="3mm">
          <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-column column-width="1cm"/>
            <fo:table-column column-width="4.5cm"/>
            <fo:table-column column-width="1cm"/>
			<fo:table-column column-width="1cm"/>
			<fo:table-column column-width="1cm"/>
            <fo:table-body>
			<fo:table-row font-weight="bold">   
			  <fo:table-cell>
        <fo:block>
          S.No.
        </fo:block>
      </fo:table-cell>
	  
	
      <fo:table-cell>
        <fo:block>
		Particular
        </fo:block>
      </fo:table-cell>
	  <fo:table-cell>
        <fo:block>
        Quantity
        </fo:block>
      </fo:table-cell>
	  <fo:table-cell>
        <fo:block>
        Rate 
        </fo:block>
      </fo:table-cell>
     
      <fo:table-cell>
        <fo:block>
        Price
        </fo:block>
      </fo:table-cell> 
</fo:table-row>   	  
             <xsl:for-each select="purchaseitems/purchaseitem">
       <fo:table-row margin-bottom="0.5cm">   
     
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="position()"/>
        </fo:block>
      </fo:table-cell>
	  
	
      <fo:table-cell>
        <fo:block>
          <xsl:value-of select="productname"/>
		  <xsl:if test="lensactive='true'">
		  <fo:table table-layout="fixed" margin-top="1mm" width="100%" border="1" border-collapse="separate">    
            <fo:table-column column-width="0.9cm"/>
			 <fo:table-column column-width="0.7cm"/>
			  <fo:table-column column-width="0.7cm"/>
			  <fo:table-column column-width="1cm"/>
			  <fo:table-column column-width="0.7cm"/>
			   <fo:table-column column-width="0.7cm"/>
			    <fo:table-column column-width="0.7cm"/>
		  <fo:table-body>
		  			<fo:table-row font-weight="bold">
					<fo:table-cell>
        <fo:block>
         Vision
        </fo:block>
      </fo:table-cell>
	  <fo:table-cell number-columns-spanned="3">
        <fo:block>
         Right Eye
        </fo:block>
      </fo:table-cell>
	  
	  <fo:table-cell number-columns-spanned="3">
        <fo:block>
         Left Eye
        </fo:block>
      </fo:table-cell>
						</fo:table-row>
						<fo:table-row >
					<fo:table-cell>
        <fo:block>
         -
        </fo:block>
      </fo:table-cell>
	  <fo:table-cell>
        <fo:block>
         SPH
        </fo:block>
      </fo:table-cell>
	  	  <fo:table-cell>
        <fo:block>
         CYL
        </fo:block>
      </fo:table-cell>
	  	  <fo:table-cell>
        <fo:block>
         AXIAL
        </fo:block>
      </fo:table-cell>
	  
	   <fo:table-cell>
        <fo:block>
         SPH
        </fo:block>
      </fo:table-cell>
	  	  <fo:table-cell>
        <fo:block>
         CYL
        </fo:block>
      </fo:table-cell>
	  	  <fo:table-cell>
        <fo:block>
         AXIAL
        </fo:block>
      </fo:table-cell>
	  </fo:table-row>
	
	<fo:table-row> 	
<fo:table-cell>
        <fo:block>
         D.V.
        </fo:block>
      </fo:table-cell>		
      <fo:table-cell>
        <fo:block>
          <xsl:value-of select="righteyes/righteye[1]/sph"/>
        </fo:block>
      </fo:table-cell>
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="righteyes/righteye[1]/cyl"/>
        </fo:block>
      </fo:table-cell>
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="righteyes/righteye[1]/axial"/>
        </fo:block>
      </fo:table-cell>
	  <fo:table-cell>
        <fo:block>
          <xsl:value-of select="lefteyes/lefteye[1]/sph"/>
        </fo:block>
      </fo:table-cell>
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="lefteyes/lefteye[1]/cyl"/>
        </fo:block>
      </fo:table-cell>
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="lefteyes/lefteye[1]/axial"/>
        </fo:block>
      </fo:table-cell>
	  </fo:table-row>   
	   <fo:table-row> 
<fo:table-cell>
        <fo:block>
         Add
        </fo:block>
      </fo:table-cell>	   
 	   	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="righteyes/righteye[2]/sph"/>
        </fo:block>
      </fo:table-cell>
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="righteyes/righteye[2]/cyl"/>
        </fo:block>
      </fo:table-cell>
	  
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="righteyes/righteye[2]/axial"/>
        </fo:block>
      </fo:table-cell>

	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="lefteyes/lefteye[2]/sph"/>
        </fo:block>
      </fo:table-cell>
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="lefteyes/lefteye[2]/cyl"/>
        </fo:block>
      </fo:table-cell>
	  
	   <fo:table-cell>
        <fo:block>
          <xsl:value-of select="lefteyes/lefteye[2]/axial"/>
        </fo:block>
      </fo:table-cell>
	   </fo:table-row>   
	  
			</fo:table-body>
			</fo:table>
		  </xsl:if>
		  
		  
        </fo:block>
	
      </fo:table-cell>
	  <fo:table-cell>
        <fo:block>
          <xsl:value-of select="buyquantity"/>
        </fo:block>
      </fo:table-cell>
	  <fo:table-cell>
        <fo:block>
          <xsl:value-of select="unitprice"/>
        </fo:block>
      </fo:table-cell>
     
      <fo:table-cell>
        <fo:block>
          <xsl:value-of select="totalcost"/>
        </fo:block>
      </fo:table-cell>   
     
    </fo:table-row>
    </xsl:for-each>
            </fo:table-body>
          </fo:table>
		   </fo:block>
		   
		 <fo:block font-size="7pt" margin-top="3mm">
      Total Amount : <xsl:value-of select="totalamount"/>
	  Amount Paid: <xsl:value-of select="initialamount"/>
	  </fo:block>
	  <fo:block font-size="7pt">
	  Balance : <xsl:value-of select="pendingamount"/>
	  Delivery Date: <xsl:value-of select="deliverydate"/>
          </fo:block>
          
           <fo:block margin-top="3mm" font-weight="italic" font-style="italic" font-size="7pt" text-align="left" >
1.Minor adjustment/fixes can be done in working hours at customer's risk.
</fo:block>
 <fo:block  font-weight="italic" font-style="italic" font-size="7pt" text-align="left" >
2.Please bring invoice copy at the time of delivery.
</fo:block>

 <fo:block  font-weight="italic" font-style="italic" font-size="7pt" text-align="left" >
3.Order once taken will not be cancelled.
</fo:block>

 <fo:block  font-weight="italic" font-style="italic" font-size="7pt" text-align="left" >
4.Delivery date is tentative.

  </fo:block>
		  
		  <fo:block  font-weight="italic" font-style="italic" font-size="7pt" text-align="right" >

		For, Amar Opticals, Korba
          </fo:block>
        </fo:flow>
      </fo:page-sequence>
     </fo:root>
</xsl:template>
</xsl:stylesheet>