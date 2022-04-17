package com.github.lyrric.service;

import com.github.lyrric.conf.Config;
import com.github.lyrric.conf.MemberConfig;
import com.github.lyrric.model.BusinessException;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketTimeoutException;

/**
 * @author wangxiaodong
 */
public class SecKillRunnable implements Runnable {

    private final Logger logger = LogManager.getLogger(SecKillRunnable.class);
    /**
     * 是否刷新st
     */
    private boolean resetSt;
    /**
     * httpService
     */
    private HttpService httpService;
    /**
     * 疫苗id
     */
    private Integer vaccineId;
    /**
     * 开始时间
     */
    private long startDate;
    /**
     * 加密参数st
     */
    private String st;
    /**
     * 接种成员ID
     */
    private Integer memberId;
    /**
     * 接种成员身份证号码
     */
    private String idCard;
    /**
     * 抢购是否成功
     * false表示疫苗已抢光
     */
    private Boolean success;

    public SecKillRunnable(boolean resetSt, HttpService httpService, MemberConfig memberConfig) {
        this.resetSt = resetSt;
        this.httpService = httpService;
        this.vaccineId = memberConfig.getVaccineId();
        this.st = memberConfig.getSt();
        this.startDate = memberConfig.getStartDate();
        this.memberId = memberConfig.getMemberId();
        this.idCard = memberConfig.getIdCard();
    }

    @Override
    public void run() {
        do {
            long id = Thread.currentThread().getId();
            try {
                //获取加密参数st
                if (resetSt) {
                    logger.info("Thread ID：{}，请求获取加密参数st", id);
                    st = httpService.getSt(vaccineId.toString());
                    logger.info("Thread ID：{}，成功获取加密参数st", id);
                }
                logger.info("Thread ID：{}，秒杀请求", id);
                httpService.secKill(vaccineId.toString(), "1", memberId.toString(), idCard, st);
                success = true;
                logger.info("Thread ID：{}，抢购成功", id);
                break;
            } catch (BusinessException e) {
                logger.info("Thread ID: {}, 抢购失败: {}", id, e.getErrMsg());
                if (e.getErrMsg().contains("没抢到")) {
                    success = false;
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } catch (ConnectTimeoutException | SocketTimeoutException socketTimeoutException) {
                logger.error("Thread ID: {},抢购失败: 超时了", Thread.currentThread().getId());
            } catch (Exception e) {
                logger.warn("Thread ID: {}，未知异常", Thread.currentThread().getId());
            } finally {
                //如果离开始时间10分钟后，或者已经成功抢到则不再继续
                if (System.currentTimeMillis() > startDate + 1000 * 60 * 10 || success != null) {
                    break;
                }
            }
        } while (true);
    }
}
