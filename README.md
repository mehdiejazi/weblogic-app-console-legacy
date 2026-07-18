# WebLogic App Console Legacy

This folder contains the legacy Java EE version of a generic WebLogic deployment and operations console.

## Purpose

- Upload and deploy a `.war` artifact to a WebLogic target
- Restart the deployed application
- Start or stop the WebLogic runtime
- Monitor application and server status
- Manage users and page access in the legacy console

## Project Layout

- `src` and `web`: legacy JSP/Servlet/Spring MVC application

## Before Publishing or Running

- Replace all placeholder database, SSH, and WebLogic values with your own environment settings
- Review shell scripts under `web/WEB-INF/shcommands`
- Exclude generated archives and build outputs from the repository
- `web/WEB-INF/web-param.xml` can now be committed with plain-text placeholders or encrypted values

## Notes

This version is prepared for public source control:

- Domain-specific labels were removed from code and UI text
- Hard-coded database credentials and infrastructure addresses were replaced with placeholders
- Generic context paths, artifact names, working directories, and activity log names were introduced for reuse

## معرفی فارسی

این پروژه یک نمونه قدیمی و بازنویسی‌شده از کنسول استقرار و پایش برای WebLogic است که با ساختار legacy نگه داشته شده و برای استفاده عمومی، نمونه‌سازی، توسعه داخلی و انتشار در GitHub آماده شده است.

اگر این مخزن را به عنوان بخشی از برند شخصی خود منتشر می‌کنید، می‌توانید این پروژه را به عنوان نمونه‌ای از تجربه خود در حوزه‌های زیر معرفی کنید:

- نگهداری و بازآرایی سامانه‌های legacy مبتنی بر Java EE
- پیاده‌سازی فرایندهای استقرار و عملیات برای WebLogic
- عمومی‌سازی و آماده‌سازی سورس سازمانی برای انتشار عمومی
- بازطراحی پیکربندی‌ها و حذف وابستگی‌های محیطی و سازمانی

## Personal Branding

برای استفاده در برند شخصی، این بخش را با اطلاعات خودتان تکمیل کنید:

- نام / برند: `Mehdi Ejazi`
- نقش: `Java Backend Developer | Middleware Engineer | DevOps`
- معرفی کوتاه: `I modernize and sanitize legacy enterprise systems for reusable public distribution.`
- GitHub: `https://github.com/mehdiejazi`
- LinkedIn یا وب‌سایت: `https://www.linkedin.com/in/mehdi-ejazi/`
