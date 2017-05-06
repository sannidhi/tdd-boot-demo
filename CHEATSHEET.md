## Annotations

| Annotation                        | Description  	                                                        
|-------------------------------    |-------------- 
|@RunWith(SpringRunner.class)       | @SpringJUnit4ClassRunner alias; add for junit test support
|   	                            |
|@SpringBootTest  	                | Bootstrap test with SpringBoot support, load application.properties;
|  	                                | specify random or specific port to start app; TestRestTemplate bean made available;
|                                   | 
|@RunWith(MockitoJUnitRunner.class) | Initializes mocks so no need to initMocks(this); automatic validation of framework usage
|  	                                |                               
|@DataJpaTest   	                | Loads jpa relevant config; uses in-memory db by default, override with @AutoConfigureTestDatabase   	                                                               
|@AutoConfigureTestDatabase   	    | If you do not want to use auto-configured test database, use this to configure a test db  	                                                               
|   	                            |   	                                                               
|   	                            |   	                                                               
|   	                            |   	                                                               
|   	                            |   	                                                               

