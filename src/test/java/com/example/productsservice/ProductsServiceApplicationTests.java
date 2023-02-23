package com.example.productsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productsservice.entites.Product;
import com.example.productsservice.repository.ProductRepository;
import com.example.productsservice.service.ProductServiceImpl;



import static org.mockito.BDDMockito.*;



import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.assertNotNull;








@SpringBootTest
class ProductsServiceApplicationTests {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;


//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String name;

//     @Column(nullable = false)
//     private String shortDescription;

//     @Column(columnDefinition = "TEXT")
//     private String longDescription;
//     @Column(nullable = false)
//     private String imageLink;
//     @Column(nullable = false)
//     private String price;                   



	@Test
	void contextLoads() {
	}

	@Test
	void getAndSetProductID(){
		Product cut = new Product();
		Long myId = 1L;
		cut.setId(myId);
		assertEquals(myId, cut.getId());
	}

	@Test
	void getAndSetProductName(){
		Product cut = new Product();
		String myName = "abc";
		cut.setName(myName);
		assertEquals(myName,cut.getName());
	}

	@Test
	void getAndSetProductShortDescription(){

		Product cut = new Product();
		String myShortDescription = "abc";
		cut.setShortDescription(myShortDescription);
		assertEquals(myShortDescription, cut.getShortDescription());

	}

	@Test
	void getAndSetProductLongDescription(){
		Product cut = new Product();
		String myLongDescription = "abc";
		cut.setLongDescription(myLongDescription);
		assertEquals(myLongDescription, cut.getLongDescription());
	}


	@Test
	void getAndSetProductImageLink(){
		Product cut = new Product();
		String myImageLink = "abc";
		cut.setImageLink(myImageLink);
		assertEquals(myImageLink, cut.getImageLink());

	}

	@Test
	void getAndSetProductPrice(){
		Product cut = new Product();
		String myProductPrice = "abc";
		cut.setPrice(myProductPrice);
		assertEquals(myProductPrice, cut.getPrice());
	}

	@Test
	void builderProduct(){

		Long myId = 1L;
		String myName = "chair";
		String myShortDescription = "good chair";
		String myLongDescription = "very good chair";
		String myImageLink = "chair image";
		String myProductPrice = "12,000";

		Product cut = Product.builder().id(myId)
		.name(myName)
		.shortDescription(myShortDescription)
		.longDescription(myLongDescription)
		.imageLink(myImageLink)
		.price(myProductPrice)
		.build();

		assertEquals(myId,cut.getId() );
		assertEquals(myName, cut.getName());
		assertEquals(myLongDescription,cut.getLongDescription());
		assertEquals(myShortDescription, cut.getShortDescription());
		assertEquals(myImageLink, cut.getImageLink());
		assertEquals(myProductPrice, cut.getPrice());

	}

	@Test
	void canSaveHotel(){
		Long myId = 1L;
		String myName = "chair";
		String myShortDescription = "good chair";
		String myLongDescription = "very good chair";
		String myImageLink = "chair image";
		String myPrice = "12,000";

		Product cut = Product.builder().id(myId)
		.name(myName)
		.shortDescription(myShortDescription)
		.longDescription(myLongDescription)
		.imageLink(myImageLink)
		.price(myPrice)
		.build();

		given(productRepository.save(cut)).willReturn(cut);
		Product savedProduct = productServiceImpl.saveProduct(cut);
		assertNotNull(savedProduct);

	}

	// getProductById(Long id);

    // public List<Product> getAllProducts();

    // public void deleteProduct(Lon

	@Test
	void canGetProductById(){
		Long myId = 1L;
		String myName = "chair";
		String myShortDescription = "good chair";
		String myLongDescription = "very good chair";
		String myImageLink = "chair image";
		String myPrice = "12,000";

		Product cut = Product.builder().id(myId)
		.name(myName)
		.shortDescription(myShortDescription)
		.longDescription(myLongDescription)
		.imageLink(myImageLink)
		.price(myPrice)
		.build();

		given(productRepository.getReferenceById(myId)).willReturn(cut);
		Product gottenProduct = productServiceImpl.getProductById(cut.getId());
		assertEquals(cut.getName(), gottenProduct.getName());
	}

	@Test
	void canGetAllProducts(){
		Product cut1 = Product.builder().id(1L)
		.name("myName")
		.shortDescription("myShortDescription")
		.longDescription("myLongDescription")
		.imageLink("myImageLink")
		.price("myPrice")
		.build();
		
		Product cut2 = Product.builder().id(2L).name("myName2")
		.shortDescription("myShortDescription2")
		.longDescription("myLongDescription2")
		.imageLink("myImageLink2")
		.price("myPrice2")
		.build();
		
		given(productRepository.findAll()).willReturn(List.of(cut1,cut2));
		List<Product> productList = productServiceImpl.getAllProducts();
		assertNotNull(productList);
		assertEquals(2, productList.size() );


	}

	@Test
	void canDeleteAProduct(){
		Long myId = 1L;
		willDoNothing().given(productRepository).deleteById(myId);
		productServiceImpl.deleteProduct(myId);
		verify(productRepository, times(1)).deleteById(myId);
	}

}
