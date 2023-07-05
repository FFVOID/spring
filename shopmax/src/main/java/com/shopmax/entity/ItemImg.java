package com.shopmax.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="item_img")
@Setter
@Getter
public class ItemImg extends BaseEntity{
	
	@Id
	@Column(name="item_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imgName; //바뀐 이미지 파일명(중복방지를 위해 바꿔서올린다)
	
	private String oriImgName; //원본 이미지 파일명
	
	private String imgUrl; //이미지 조회 경로
	
	private String repimgYn; //대표 이미지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	//이미지에 대한 정보를 업데이트 하는 메소드
	public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName = oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
}
