/* 
 * File: ipac.js
 * Desc: Common js namespace for ipac app
 * 
 */
/******
*
* Namespace: ipac - contains all IPAC application Javascript
*
*******/
ipac = {
    
    _subnetArr: [],
    
    /******
    *
    * Function: getNextAvailableIp - Function call URI asyncronously to get next available IP as string
    * Returns:  void
    *
    *******/
    getNextAvailableIp: function(){
        
        var subnetId = ipac.getSubnetFromSelect();
        
        if(subnetId){
            var url = "/subnets/nextAvailable.json?subnetId="+subnetId;
        
            //Add random number to URL to stop caching
            var noCache = "nocache="+Math.random()*1234567;
            
            //console.log('Getting next available IP with uri: '+url);

            //ajax call to server for next IP
            $.ajax({
                url: url,
                dataType: 'json',
                data: noCache,
                cache: false,
                success: function(result){
                    //pass to display function
                    ipac.setIpInputBox(result[0]);
                },
                error: function(xhr, textStatus, errorThrown){
                    // Handle error
                    $("#status-update").text('Error occured: '+errorThrown);
                }

            })
        }else{
            $("#status-update").text('');
        }
        
        
    },
    
    /******
    *
    * Function: getSubnetFromSelect - Get the subnet id value from select
    * Returns:  int
    *
    *******/    
    getSubnetFromSelect: function(){
        //console.log('Found subnet id: '+$("#subnetId").val());
        $("#status-update").text('Retrieving next available IP address...');
        return $("#subnetId").val();

    },
    
    /******
    *
    * Function: setIpInputBox - Sets the IP input text box with sttring
    * Returns:  void
    *
    *******/     
    setIpInputBox: function(ipAddrStr){
        //console.log('Setting ip address');
        $("#status-update").text('');
        $("#ipAddress").val(ipAddrStr);
    },
    
    /******
    *
    * Function: getListSubnets - Function call URI asyncronously to get subnet data
    * Returns:  void
    *
    *******/
    getListSubnets: function() {
        
        if(ipac.getVlanFromSelect() !== null){
            
            var url = "/subnets.json?vlanId="+ipac.getVlanFromSelect()
            //console.log('Getting vlan data with this uri: '+url);
            //ajax call to server for next IP
            $.ajax({
                url: url,
                dataType: 'json',
                data: null,
                success: function(result){
                    //pass to display function
                    ipac.setSubnetBox(result);
                },
                error: function(xhr, textStatus, errorThrown){
                    // Handle error
                    $("#status-update").text('Error occured: '+errorThrown);
                }
            })
            
        }else{
            //console.log('error');
        }
        
    },

    /******
    *
    * Function: getVlanFromSelect - Get the vlan id value from select
    * Returns:  int
    *
    *******/    
    getVlanFromSelect: function(){
        //console.log('Found vlan id: '+$("#vlanId").val());
        if($("#vlanId").val() !== '0'){
            $("#status-update").text('Retrieving subnet data...');
            return $("#vlanId").val();
        }else{
            $("#status-update").text('Error selecting vlan. Try again.');
            return null;
        }

    },
    
    /******
    *
    * Function: setSubnetBox - Sets the Subnet text box with sttring
    * Returns:  void
    *
    *******/     
    setSubnetBox: function(data){
        //console.log('Writing data to subnet box');
        $("#status-update").text('');
        ipac.setIpInputBox('');
        $("#subnetName").val(data[0].ipAddress);
        $("#subnetId").val(data[0].id);
    }    
}

