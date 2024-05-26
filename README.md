# 막내야 뭐 먹을래?

## 🚩목차

1. [프로젝트 요약](##📸-프로젝트-요약)
2. [기획 배경](##🎈-기획-배경)
3. [주요 화면](##🎥-주요-화면)
4. [기술 스택](##⚙-기술-스택)
5. [기타 산출물](##📕-기타-산출물)

## 📸 프로젝트 요약

한 줄 설명 : 직장 내 막내들을 위한 음식 선호도 기반 점심 메뉴 추천 서비스

서비스명 : **막내야 뭐 먹을래?** (막.뭐.먹)

기간 : 2024/02/19 ~ 2024/04/05, 6주

인원 : 장현수(팀장, FE), 김규현(BE, Infra), 권기용(BE), 이다은(FE), 한규준(BE)

## 🎈 기획 배경

### 목적

- 모임 및 회사에서 매일 새로운 메뉴를 고민하는 막내들의 부담을 줄여준다.
- 음식 선호도 및 먹은 음식 기록을 토대로 취향에 맞는 메뉴를 추천한다.

### 현재 상황

- 의뢰인이 출근하는 첫날부터 점심 메뉴 선택의 늪이 시작됐다. 그는 “코로나19로 점심을 배달해서 먹는 상황이어서 선배들이 막내인 제가 먹고 싶은 걸로 시키라고 했다”며 메뉴 선정은 자연스럽게 본인의 일이 됐다고 밝혔다. -> ['무엇이든 물어보살' 점심 메뉴 때문에 퇴사한 직장인 사연에 서장훈이 “희한한 갑질"이라며 분노했다](https://www.huffingtonpost.kr/news/articleView.html?idxno=107008)



## ⚙ 기술 스택

Frontend - React 18.2.0, tanstack-query 5.28.4, type-script 5.4.3, zustand 4.5.2

<div style="justify-content: center; display: flex">
<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
<img src="https://img.shields.io/badge/reactquery-FF4154?style=for-the-badge&logo=reactquery&logoColor=black">
<img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white">
<img src="https://img.shields.io/badge/zustand-61DAFB?style=for-the-badge&logo=zustand&logoColor=black">
</div>

Backend - Java21 , Spring boot 3.2.1, JPA 3.1.0, MariaDB 10.3.23, Redis 7.2.4
<div style="justify-content: center; display: flex">
  <img src="https://img.shields.io/badge/java-B1361E?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
</div>
Infra - AWS(EC2, S3), Docker, Jenkins, Nginx

<div style="justify-content: center; display: flex">
  <img src="https://img.shields.io/badge/Mariadb-003545?style=for-the-badge&logo=MariaDB&logoColor=white">
  <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
  <img src="https://img.shields.io/badge/ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
  <img src="https://img.shields.io/badge/s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/gitlab_cicd-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white">
  <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
  
</div>



## 🎥 주요 화면

### 랜딩 페이지


<img src='https://velog.velcdn.com/images/kgh2120/post/5fbdfb99-1a1b-4166-805c-eac9c4c9b127/image.gif' width='300' height='600'>

### 먹비티아이 검사

<!-- ![mbti](/uploads/3f5067ceacf17b8a142b512d46e7e7b9/mbti.gif) -->
<img src='https://velog.velcdn.com/images/kgh2120/post/2cda7b3f-d754-4ced-9c9a-bc4a25b93f12/image.gif' width='300' height='600'>

### 회원가입

<img src='https://velog.velcdn.com/images/kgh2120/post/29a7b972-c542-4397-911e-89094308f978/image.gif' width='800' height='600'>

### 로그인

<!-- ![login](/uploads/cc81bae212dd78567199f09624c7ecc4/login.gif) -->
<img src='https://velog.velcdn.com/images/kgh2120/post/a0391ab4-0af5-4422-b647-e0b351076461/image.gif' width='300' height='600'>

### 개인정보 수정

<!-- ![profile](/uploads/35de9344017864161c8e232b24d30420/profile.gif) -->

<img src='https://velog.velcdn.com/images/kgh2120/post/9965bae4-9f37-4b3e-ba51-1577fddd6401/image.gif' width='300' height='600'>

### 개인먹비티아이 조회

<img src='https://velog.velcdn.com/images/kgh2120/post/48bbbce6-7046-4db6-9ff6-ece8ca4e916d/image.gif' width='300' height='600'>
![](https://velog.velcdn.com/images/kgh2120/post/e3e23abf-54d3-421d-b340-5f958d4715e8/image.gif)

### 메뉴 추천

<!-- ![mainpage](/uploads/2ae2656afca9af511eed47a3ee876992/mainpage.gif) -->
<img src='https://velog.velcdn.com/images/kgh2120/post/bb83c303-7fe7-4812-8e21-0284ba5b164a/image.gif' width='300' height='600'>

- 먹비티아이를 바탕으로 한 메뉴추천
  (그 외 메뉴를 원할 시 룰렛 돌리기로 선택지 증가)

- 날씨별 추천
- 먹비티아이, 기록을 기반으로 새로운 메뉴 추천

### 그룹정보 수정(대표사진, 그룹명)

<img src='https://velog.velcdn.com/images/kgh2120/post/30cfa0b0-4ebe-4ef3-b06a-4a1499f38eb3/image.gif' width='800' height='600'>

### 먹봇 생성, 삭제(미가입한 임의유저)

<!-- ![mukbot](/uploads/15f45ca6252282f8e568b539fb734c0b/mukbot.gif) -->
<img src='https://velog.velcdn.com/images/kgh2120/post/6c75f8d2-bef9-445a-9695-1ab5629465aa/image.gif' width='300' height='600'>

### 오늘의 멤버 변경

<!-- ![member_change](/uploads/12eb397f5005be39799b1275d0911b55/member_change.gif) -->

<img src='https://velog.velcdn.com/images/kgh2120/post/372ad81d-4ddb-45ca-838b-c6e2f73cc907/image.gif' width='300' height='600'>

### 먹은 음식 기록

<img src='https://velog.velcdn.com/images/kgh2120/post/2635e773-e8ff-47eb-93ab-c4df64d552e4/image.gif' width='300' height='600'>

### 먹보(가입한 유저) 초대

<img src='https://velog.velcdn.com/images/kgh2120/post/638430c0-4879-40ac-89bb-3d8b6d745de2/image.gif' width='300' height='600'>

### 먹적 조회 및 대표먹적 변경

<img src='https://velog.velcdn.com/images/kgh2120/post/bfddf758-e1a5-45ae-ba0a-46c8c135ac25/image.gif' width='300' height='600'>



## 📕 기타 산출물

### 아키텍처

<img src='https://velog.velcdn.com/images/kgh2120/post/2031b836-2233-47de-8ee8-64cdf9a3f5c7/image.png' width='1200' height='600'>

### ERD

<img src='https://velog.velcdn.com/images/kgh2120/post/bfe74691-3673-430c-831e-a76ba40d186c/image.png' width='1200' height='800'>

### API 명세서

<img src='https://velog.velcdn.com/images/kgh2120/post/d5e5d335-137a-4de9-881c-60539ccb5173/image.png' width='1200' height='600'>
