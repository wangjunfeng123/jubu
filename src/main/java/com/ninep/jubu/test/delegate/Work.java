package com.ninep.jubu.test.delegate;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/4
 */
public class Work implements Target{

    private Target target;
    public Work(Target target) {
        this.target = target;
    }


    @Override
    public void doing() {
        target.doing();
    }

}