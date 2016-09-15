//angular.module('RDash', ['ui.bootstrap', 'ui.router', 'ngCookies']);
//'use strict';

angular.module('RDash').controller("posititionTbl", function($scope) 
{
	$scope.positions = [
	                   {company:'Amtek Inc', symbol:'AME', assetType:'EQ', exchange:'NYSE', quantity:2500, price:47.80},
	                   {company:'Alibaba Group Holding Ltd', symbol:'BABA', assetType:'EQ', exchange:'NYSE', quantity:2500000, price:78.75},
	                   {company:'Alibaba Corporation', symbol:'ALIB', assetType:'EQ', exchange:'NYSE', quantity:225, price:225},
	                   {company:'GS GOLD ETF 2016', symbol:'GSGOLD', assetType:'INDEX', exchange:'NYSE', quantity:5000, price:1225.65},
	                   {company:'SAMSUNG GLOBAL CORP', symbol:'SAMSUNG', assetType:'EQ', exchange:'NASDAC', quantity:2500, price:65},
	                   {company:'BAYER CHEMICALS', symbol:'BAYC', assetType:'EQ', exchange:'NYSE', quantity:600, price:22.50}
	                  ];
	
	$scope.addRow = function(){		
		$scope.position.push({ company:$scope.company, symbol: $scope.symbol, assetType:$scope.assetType, exchange: $scope.exchange, quantity:$scope.quantity, price:$scope.price });
		$scope.company='';
		$scope.symbol='';
		$scope.assetType='';
		$scope.exchange='';
		$scope.qyantity='';
		$scope.price='';
	};

}
);

angular.module('RDash').controller("demandTbl", function($scope) 
		{
			$scope.demands = [
			                   {date:'28 MAY 2016 11:26 AM', firm:'JP Morgan NY Desk', region:'NY', assetType:'CASH', exchange:'NYSE', symbol:'APPL', quantity:150, wallet:'Peter@JPM NY DESK', track:"Cash demand for OTC Trade ID:TRD2665N"},
			                   {date:'28 MAY 2016 14:26 PM', firm:'BlackRock Hedge Fund', region:'NY', assetType:'CASH', exchange:'NYSE',  symbol:'SAMSUNG', quantity:50, wallet:'Mathew@BLackRock Desk', track:"Cash demand for Trade ID:TRD6894B"}
			                  ];
		}
		);

angular.module('RDash').controller("transTbl", function($scope) 
		{
			$scope.transactions = [
			                   {date:'28 MAY 2016 14:26 PM', firm:'BlackRock Hedge Fund', region:'NY', assetType:'CASH', exchange:'NYSE',  symbol:'SAMSUNG', quantity:50, wallet:'Mathew@BLackRock Desk', track:"Sold SAMSUNG Stock : ID:TRD7985A", bsInd:'S', price:92.25},
			                   {date:'28 MAY 2016 11:26 AM', firm:'JP Morgan NY Desk', region:'NY', assetType:'CASH', exchange:'NYSE', symbol:'APPL', quantity:45, wallet:'Peter@JPM NY DESK', track:"Bought APPL Stock : ID:TRD6515B", bsInd:'B', price:90.05},
			                   {date:'28 MAY 2016 11:22 AM', firm:'JP Morgan NY Desk', region:'NY', assetType:'CASH', exchange:'NYSE', symbol:'APPL', quantity:45, wallet:'Peter@JPM NY DESK', track:"Bought APPL Stock : ID:TRD6402B", bsInd:'B', price:91.11},
			                   {date:'28 MAY 2016 11:15 AM', firm:'JP Morgan NY Desk', region:'NY', assetType:'CASH', exchange:'NYSE', symbol:'APPL', quantity:35, wallet:'Peter@JPM NY DESK', track:"Bought APPL Stock : ID:TRD6390B", bsInd:'B', price:96.85},
			                   {date:'28 MAY 2016 11:06 AM', firm:'JP Morgan NY Desk', region:'NY', assetType:'EQ', exchange:'NYSE', symbol:'APPL', quantity:25, wallet:'Peter@JPM NY DESK', track:"Bought APPL Stock : ID:TRD6385B", bsInd:'B', price:65.0},
			                   
			                  ];
		}
		);




//For cash Balance
angular.module('RDash').controller("cashBalance", function($scope, $interval, $http) 
		{
			$scope.cash = 0;
			
			$scope.getCashBalance = function() 
			{
			    $http.get('http://localhost:8080/ChaosGlt-0.4/getWalletDetails?walletId=1YyeY8QrH2z6Pox45TextHWiqdVoWKsapEPGbg').
			        success(function(data) 
			        {
			        	console.log("Return data : " + data['assetsMap']['CASH']);
		            	$scope.cash = data['assetsMap']['CASH']['quantity'];
			        });
			}
			
			$scope.getCashBalance();
			
			$interval(function(){
			      $scope.getCashBalance();
			    },5000)

		}
);
