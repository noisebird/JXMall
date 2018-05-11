insert into product(name,description,price,inventoryId) values("test666","test",1000,1);
insert into product(name,description,price,inventoryId) values("方便面","康师傅",5,2);
insert into product(name,description,price,inventoryId) values("农夫山泉","矿泉水",3,3);
insert into product(name,description,price,inventoryId) values("三颗松鼠大礼包","七种零食包装",60,4);
insert into product(name,description,price,inventoryId) values("飘柔洗护套装","滋润秀发",110,5);

insert into inventory(count,lockedCount) values(113,107);
insert into inventory(count,lockedCount) values(100,86);
insert into inventory(count,lockedCount) values(100,85);
insert into inventory(count,lockedCount) values(123,28);
insert into inventory(count,lockedCount) values(145,58);


insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "2018-4-20", "2018-4-14", "finished", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);


insert into productShoot(orderId,productId,productName,productDescription,purchasePrice,purchaseCount) values(1,4,"三颗松鼠大礼包","七种零食包装",60,2),(1,5,"飘柔洗护套装","滋润秀发",110,1);

insert into logistics(logisticsStatus,outboundTime,signedTime,deliveryMan,orderId) values("readyToShip","","","李师傅",1);