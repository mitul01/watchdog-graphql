INSERT INTO bot VALUES ('0025388d-58dd-487e-a033-f832b73ed2df','2024-04-06 16:58:06.427915','TELEGRAM');

INSERT INTO userbase VALUES ('614cf855-7700-4ae5-bd8d-0d9c94cb0502','2024-04-06 16:58:06.427915','testUser1');
INSERT INTO bots_installed_by_user VALUES ('614cf855-7700-4ae5-bd8d-0d9c94cb0502','0025388d-58dd-487e-a033-f832b73ed2df');
INSERT INTO account VALUES ('d6680059-bcf3-47b1-8076-cb8e2488ea56','2024-04-06 16:58:06.427915','PERSONAL');
INSERT INTO user_accounts VALUES ('d6680059-bcf3-47b1-8076-cb8e2488ea56','614cf855-7700-4ae5-bd8d-0d9c94cb0502');

INSERT INTO userbase VALUES ('c8d0eac4-0a03-48b4-99c9-81104d56a964','2024-04-06 16:58:06.427915','testUser2');
INSERT INTO bots_installed_by_user VALUES ('c8d0eac4-0a03-48b4-99c9-81104d56a964','0025388d-58dd-487e-a033-f832b73ed2df');
INSERT INTO account VALUES ('d1b9cdaf-71e7-46e9-94c0-6e788931e49a','2024-04-06 16:58:06.427915','PERSONAL');
INSERT INTO user_accounts VALUES ('d1b9cdaf-71e7-46e9-94c0-6e788931e49a','c8d0eac4-0a03-48b4-99c9-81104d56a964');


INSERT INTO userbase VALUES ('333530e8-31ee-4e06-9a9a-82970d7286e5','2024-04-06 16:58:06.427915','testUser3');
INSERT INTO bots_installed_by_user VALUES ('333530e8-31ee-4e06-9a9a-82970d7286e5','0025388d-58dd-487e-a033-f832b73ed2df');
INSERT INTO account VALUES ('e1234b92-5e7b-4bde-bea6-a69014b88510','2024-04-06 16:58:06.427915','PERSONAL');
INSERT INTO user_accounts VALUES ('e1234b92-5e7b-4bde-bea6-a69014b88510','333530e8-31ee-4e06-9a9a-82970d7286e5');

INSERT INTO userbase VALUES ('c13e6637-458b-4523-8da5-834429247b9a','2024-04-06 16:58:06.427915','testUser4');
INSERT INTO bots_installed_by_user VALUES ('c13e6637-458b-4523-8da5-834429247b9a','0025388d-58dd-487e-a033-f832b73ed2df');
INSERT INTO account VALUES ('38bfdffa-5a89-42bc-80d7-5684b39f9427','2024-04-06 16:58:06.427915','PERSONAL');
INSERT INTO user_accounts VALUES ('38bfdffa-5a89-42bc-80d7-5684b39f9427','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO account VALUES ('724e537a-2316-4356-ae6c-e1b576dffdd3','2024-04-06 16:58:06.427915','GOA TRIP');
INSERT INTO user_accounts VALUES ('724e537a-2316-4356-ae6c-e1b576dffdd3','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO user_accounts VALUES ('724e537a-2316-4356-ae6c-e1b576dffdd3','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO user_accounts VALUES ('724e537a-2316-4356-ae6c-e1b576dffdd3','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO user_accounts VALUES ('724e537a-2316-4356-ae6c-e1b576dffdd3','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO expense VALUES ('c0030da3-ffde-498a-9fcb-b5fe4204762d','2024-04-06 16:58:06.427915',400,'Uber','724e537a-2316-4356-ae6c-e1b576dffdd3','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('1c591b8a-cd0f-49b7-a67f-6e8f599f8207',100,'Uber','c0030da3-ffde-498a-9fcb-b5fe4204762d','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('7ebdace8-b943-43f9-85fa-8a9f5c95b432',100,'Uber','c0030da3-ffde-498a-9fcb-b5fe4204762d','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('118f3ba7-0cdc-4349-9fec-d87931ba984a',100,'Uber','c0030da3-ffde-498a-9fcb-b5fe4204762d','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('ea7e9bc8-18b8-4be0-965f-99a56ec35360',100,'Uber','c0030da3-ffde-498a-9fcb-b5fe4204762d','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO expense VALUES ('a817a039-872a-47ec-9c54-f28fb973ff8f','2024-04-06 16:58:06.427915',100,'Food','724e537a-2316-4356-ae6c-e1b576dffdd3','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('ee0e0a86-b55e-4256-b955-06d4a3410f0f',25,'Food','a817a039-872a-47ec-9c54-f28fb973ff8f','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('f8ef9c43-1be6-419c-aeb9-54482f4669fe',25,'Food','a817a039-872a-47ec-9c54-f28fb973ff8f','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('a8ef9c43-1be6-419c-aeb9-54482f4669f1',25,'Food','a817a039-872a-47ec-9c54-f28fb973ff8f','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('f8ef9c43-1be6-419c-aeb9-54482f4669f2',25,'Food','a817a039-872a-47ec-9c54-f28fb973ff8f','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO expense VALUES ('4d6314bc-7169-4bfa-9c5b-e67bbc126b7d','2024-04-06 16:58:06.427915',1000,'Food','724e537a-2316-4356-ae6c-e1b576dffdd3','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('ba34fc3c-4ad4-4707-afc6-dbfae8dfd061',250,'Food','4d6314bc-7169-4bfa-9c5b-e67bbc126b7d','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('ba34fc3c-4ad4-4707-afc6-dbfae8dfd062',250,'Food','4d6314bc-7169-4bfa-9c5b-e67bbc126b7d','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('ba34fc3c-4ad4-4707-afc6-dbfae8dfd063',250,'Food','4d6314bc-7169-4bfa-9c5b-e67bbc126b7d','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('ba34fc3c-4ad4-4707-afc6-dbfae8dfd064',250,'Food','4d6314bc-7169-4bfa-9c5b-e67bbc126b7d','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO expense VALUES ('0dc6f699-f5f7-45b5-ad8b-d1df26bc9da4','2024-04-06 16:58:06.427915',100,'Ola','724e537a-2316-4356-ae6c-e1b576dffdd3','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('3e63f7d5-49cd-4fb3-a694-e757288536dc',25,'Ola','0dc6f699-f5f7-45b5-ad8b-d1df26bc9da4','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('3e63f7d5-49cd-4fb3-a694-e757288536dd',25,'Ola','0dc6f699-f5f7-45b5-ad8b-d1df26bc9da4','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('3e63f7d5-49cd-4fb3-a694-e757288536de',25,'Ola','0dc6f699-f5f7-45b5-ad8b-d1df26bc9da4','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('3e63f7d5-49cd-4fb3-a694-e757288536df',25,'Ola','0dc6f699-f5f7-45b5-ad8b-d1df26bc9da4','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO expense VALUES ('9d28c509-ba65-4a2d-afc0-d023e32c30f2','2024-04-06 16:58:06.427915',100,'Ola','724e537a-2316-4356-ae6c-e1b576dffdd3','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('1d28c509-ba65-4a2d-afc0-d023e32c30f9',25,'Ola','9d28c509-ba65-4a2d-afc0-d023e32c30f2','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('3d28c509-ba65-4a2d-afc0-d023e32c30f8',25,'Ola','9d28c509-ba65-4a2d-afc0-d023e32c30f2','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('6d28c509-ba65-4a2d-afc0-d023e32c30f7',25,'Ola','9d28c509-ba65-4a2d-afc0-d023e32c30f2','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('7d28c509-ba65-4a2d-afc0-d023e32c30f6',25,'Ola','9d28c509-ba65-4a2d-afc0-d023e32c30f2','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO expense VALUES ('a6fd3573-8191-465c-89ce-7fb94291a3f3','2024-04-06 16:58:06.427915',400,'Food','724e537a-2316-4356-ae6c-e1b576dffdd3','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('170c7085-1747-43d4-a159-ee8aa0aaeace',100,'Food','a6fd3573-8191-465c-89ce-7fb94291a3f3','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('14458fa6-cf6e-46a1-88a9-e184964e4d1e',100,'Food','a6fd3573-8191-465c-89ce-7fb94291a3f3','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('b18259a4-0fd9-43f5-b401-881537361b98',100,'Food','a6fd3573-8191-465c-89ce-7fb94291a3f3','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('acef29c9-ec51-4adc-9df3-c40eb74fe85a',100,'Food','a6fd3573-8191-465c-89ce-7fb94291a3f3','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO expense VALUES ('f6d06595-7bc0-49cb-b867-9b2de5de0121','2024-04-06 16:58:06.427915',200,'Uber','724e537a-2316-4356-ae6c-e1b576dffdd3','c13e6637-458b-4523-8da5-834429247b9a');
INSERT INTO expense_split VALUES ('111e58ea-071f-4a34-a099-d77f1e7929fd',50,'Uber','f6d06595-7bc0-49cb-b867-9b2de5de0121','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('daa1de44-4477-42fd-979a-4097ef4767de',50,'Uber','f6d06595-7bc0-49cb-b867-9b2de5de0121','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('714f5cf4-9fa3-4fab-b20b-8fa30ff73141',50,'Uber','f6d06595-7bc0-49cb-b867-9b2de5de0121','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('2c623d41-478d-4bcf-8d9b-c4c560e1fd1b',50,'Uber','f6d06595-7bc0-49cb-b867-9b2de5de0121','c13e6637-458b-4523-8da5-834429247b9a');

INSERT INTO expense VALUES ('831c5824-c97e-4a94-b457-5796175ad125','2024-04-06 16:58:06.427915',400,'Food','724e537a-2316-4356-ae6c-e1b576dffdd3','c13e6637-458b-4523-8da5-834429247b9a');
INSERT INTO expense_split VALUES ('37aff8c0-a825-4742-a0a9-ae02c7744a3d',100,'Food','831c5824-c97e-4a94-b457-5796175ad125','614cf855-7700-4ae5-bd8d-0d9c94cb0502');
INSERT INTO expense_split VALUES ('68e390b7-a1be-410a-9c2a-7dc58a5ff9b7',100,'Food','831c5824-c97e-4a94-b457-5796175ad125','c8d0eac4-0a03-48b4-99c9-81104d56a964');
INSERT INTO expense_split VALUES ('6440be90-85e1-4760-9124-7ce9236dd815',100,'Food','831c5824-c97e-4a94-b457-5796175ad125','333530e8-31ee-4e06-9a9a-82970d7286e5');
INSERT INTO expense_split VALUES ('75295d52-4501-40a5-a2af-0a88fb0aa8b5',100,'Food','831c5824-c97e-4a94-b457-5796175ad125','c13e6637-458b-4523-8da5-834429247b9a');

