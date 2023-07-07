package com.shopmax.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shopmax.dto.ItemSearchDto;
import com.shopmax.dto.MainItemDto;
import com.shopmax.entity.Item;

public interface ItemRepositoryCustom {
	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable); //페이지처리(page에서)
	
	Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable); //페이지처리
}


