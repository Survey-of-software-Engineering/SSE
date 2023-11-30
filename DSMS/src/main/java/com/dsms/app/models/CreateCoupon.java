package com.dsms.app.models;

import com.dsms.app.entity.CouponCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCoupon {

    String validity;
    CouponCode couponCode;
}
