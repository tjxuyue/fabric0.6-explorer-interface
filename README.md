# Hyperledger Fabric 0.6 区块链浏览器 接口服务  


##获取当前区块高度和区块信息  
/getBlockNum   
##获取节点列表  
/getPeerList  
##获取指定区块信息  
/getBlock/{num}  
##获取指定交易信息  
/getTransaction/{txid}  
  
##记账合约查询  
/query/{chaincodeid}/{key}  
##记账合约信息上链  
/invoke/put/{chaincodeid}?key={key}&valu={value}  


