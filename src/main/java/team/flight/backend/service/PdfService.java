package team.flight.backend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.flight.backend.global.exception.AppException;
import team.flight.backend.global.exception.ErrorCode;

@Service
@RequiredArgsConstructor
public class PdfService {

    public File generatePdfFromHtml(String htmlContent, String outputPdfPath) throws IOException, InterruptedException {
        // HTML 내용을 임시 파일에 저장
        File htmlFile = File.createTempFile("temp-html", ".html");
        try (OutputStream outputStream = new FileOutputStream(htmlFile)) {
            outputStream.write(htmlContent.getBytes());
        }
        // ProcessBuilder를 사용하여 wkhtmltopdf 실행
        ProcessBuilder processBuilder = new ProcessBuilder(
                "/usr/bin/wkhtmltopdf",
                "--page-width", "1600px",
                "--page-height", "900px",
                htmlFile.getAbsolutePath(),
                outputPdfPath
        );

        // 프로세스 실행
        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        // 성공적으로 PDF가 생성되면 파일 반환
        if (exitCode == 0) {
            return new File(outputPdfPath);
        } else {
            throw new AppException(ErrorCode.PDF_CONVERT_EXCEPTION);
        }
    }
}
