package com.konantech.spring.core;

import com.konantech.spring.domain.JobStatus;
import com.konantech.spring.util.JSONUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Component
public class InitalizingCore {

    private static Logger logger = LoggerFactory.getLogger(InitalizingCore.class);

    @Value("${demo.inputPath}")
    private String inputPath;

    @Value("${demo.progressPath}")
    private String progressPath;

    @Value("${demo.resultPath}")
    private String resultPath;

    public void job() throws Exception {

        String jobId = null;
        try {
            File sourcePath = new File(inputPath);
            if (!sourcePath.exists() || !sourcePath.isDirectory()) {
                throw new Exception("디렉토리가 존재 하지 않습니다. ( " + sourcePath.getAbsolutePath() + ')');
            }

            String[] extensions = new String[]{"json"};
            List<File> fileList = (List<File>) FileUtils.listFiles(sourcePath, extensions, false);
            if (fileList.size() > 0) {
                init();
                File sourceFile = fileList.get(0);
                String json = FileUtils.readFileToString(sourceFile, "UTF-8");
                Map jsonMap = JSONUtil.jsonStringToMap(json);
                jobId = MapUtils.getString(jsonMap, "jobId");

                if (StringUtils.isEmpty(jobId)) {
                    stats(jobId, "FAIL", 0, "jobID 가 없습니다.");
                    throw new Exception("jobID 가 없습니다.");
                }
                stats(jobId, "READY", 0, "");
                for (int i = 0; i < 100; i++) {
                    stats(jobId, "RUNNING", i, "");
                    Thread.sleep(200);
                }
                stats(jobId, "COMPLETE", 0, "");
                result();
                FileUtils.deleteQuietly(sourceFile);
            }

        } catch (Exception e) {
            stats(jobId, "FAIL", 0, "jobID 가 없습니다.");
            logger.error(e.getMessage(), e);
        }
    }

    public void init() throws Exception {
        File targetPath = new File(progressPath);
        if (!targetPath.exists() || !targetPath.isDirectory()) {
            throw new Exception("디렉토리가 존재 하지 않습니다. ( " + targetPath.getAbsolutePath() + ')');
        }
        File targetFile = new File(progressPath, "progress.json");
        if (targetFile.exists()) {
            FileUtils.deleteQuietly(targetFile);
        }
    }


    public void stats(String jobId, String status, float jobProgress, String message) throws Exception {
        File targetPath = new File(progressPath);
        if (!targetPath.exists() || !targetPath.isDirectory()) {
            throw new Exception("디렉토리가 존재 하지 않습니다. ( " + targetPath.getAbsolutePath() + ')');
        }
        File targetFile = new File(progressPath, "progress.json");

        Date date = new Date();
        String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT);
        TimeZone utc = TimeZone.getTimeZone("UTC");
        sdf.setTimeZone(utc);

        JobStatus jobStatus = new JobStatus();
        jobStatus.setJobId(jobId);
        jobStatus.setProgress(jobProgress);
        jobStatus.setTime(sdf.format(date));
        jobStatus.setState(status);
        jobStatus.setMessage(message);
        String json = JSONUtil.jsonStringFromObject(jobStatus);

        FileUtils.writeStringToFile(targetFile, json, "UTF-8");
        System.out.println(json);
        logger.debug(json);
    }

    public void result() throws Exception {
        File targetPath = new File(resultPath);
        if (!targetPath.exists() || !targetPath.isDirectory()) {
            throw new Exception("디렉토리가 존재 하지 않습니다. ( " + targetPath.getAbsolutePath() + ')');
        }
        File targetFile = new File(resultPath, "result.json");
        String json = "[\n" +
                "\"/recognitionVolume/Actor/Feature/20/158/MP0000025772_81328.pikle\",\n" +
                "\"/recognitionVolume/Actor/Feature/26/589/MP0000009683_81328.pikle\"\n" +
                "]";
        FileUtils.writeStringToFile(targetFile, json, "UTF-8");
        System.out.println(json);
        logger.debug(json);

    }
}