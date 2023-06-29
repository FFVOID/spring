package com.shopmax.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.shopmax.entity.Item;
import com.shopmax.constant.ItemSellStatus;


                          //<해당 repository에서 사용할 Entity,Entity클래스의 기본키 타입>
public interface ItemRepository extends JpaRepository<Item, Long> {
			
	//select * from item where item_nm = ? 
	List<Item> findByItemNm(String itemNm);
	
	//select * from item where item_nm = ? and item_sell_status = ?
	List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);
	
	//select * from item where price between 10004 and 10008;
	List<Item> findByPriceBetween(int price1, int price2);
	
	//select * from item where regtime >= ? ;
	List<Item> findByRegTimeAfter(LocalDateTime regTime);
	
	
	List<Item> findByItemSellStatusNotNull();
	
	
	List<Item> findByItemDetailLike(String itemDetail);
	
	
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	
	List<Item> findByPriceBeforeOrderByPriceDesc(int price1);
	
	//JPQL(non native 쿼리) -> 컬럼명, 테이블명은 반드시시 엔티티 클래스 기준으로 작성 별칭(as)을 지어줘야함
	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail")String itemDetail);
	
	//JPQL(native 쿼리) -> h2 데이터베이스를 기준으로한 쿼리문 작성
	@Query(value = "select * from item where item_detail like %:itemDetail% order by price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	@Query(value = "select i from Item i where i.price >=:price")
	List<Item> findByPrice(@Param("price") int price);
	
	@Query(value = "select i from Item i where i.itemNm = :itemNm and i.itemSellStatus = :itemSellStatus")
	List<Item> findByitemNm2(@Param("itemNm") String itemNm, @Param("itemSellStatus") ItemSellStatus itemSellStatus);
}
