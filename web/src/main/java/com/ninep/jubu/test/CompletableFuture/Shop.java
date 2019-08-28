package com.ninep.jubu.test.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/3/24
 */
public class Shop {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop(String name) {
        this.name = name;
    }

    private Random random = new Random();


    List<Shop> shopList = Arrays.asList(new Shop("Bs"),
            new Shop("ddd"),
            new Shop("ff"),
            new Shop("buyIt"),
            new Shop("on tv"));


    public List<String> getPrices(String product) {
        return shopList.stream()
                .map(shop -> String.format("price is %s",shop.getName()))
                .collect(Collectors.toList());
    }



    public void test() {
        Shop shop = new Shop("zha");
        long start = System.nanoTime();
        Future<Double> future = shop.getPriceAnysc("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1000000;
        System.out.println(invocationTime);
        doSomethings();

        try {
            Double price = future.get();

        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (Exception e) {
        }
    }

    private void doSomethings() {
    }

    /**
     * 简化的方法
     * @param product 产品
     * @return
     */
    public Future<Double> getPriceAnysc2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public Future<Double> getPriceAnysc(String product) {
        CompletableFuture futurePrice = new CompletableFuture();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    private double getPrice(String product) {
        return calculatePrice(product);
    }

    // 计算
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }


    public static void delay() {
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            throw new RuntimeException("TIME OUT");
        }
    }

}