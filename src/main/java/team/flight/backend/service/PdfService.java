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

        // wkhtmltopdf 명령어 실행 (html 파일 -> pdf 파일로 변환)
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("wkhtmltopdf",
                "--page-width", "1920px",
                "--page-height", "1080px",
                htmlFile.getAbsolutePath(),
                outputPdfPath);

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            return new File(outputPdfPath); // 생성된 PDF 파일 반환
        } else {
            throw new AppException(ErrorCode.PDF_CONVERT_EXCEPTION);
        }
    }
}
