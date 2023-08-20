package com.example.mycoursesapp

import androidx.annotation.DrawableRes

data class CourseModel(
    val rating: Float,
    val title: String,
    @DrawableRes val thumbnail: Int,
    val body: String
)


val course1 = CourseModel(
    1.5f,
    "Android Developer Course",
    R.drawable.previewpage0,
    """ 
        Requirements
        Some prior experience in system architecture and computer science is expected.
        Description
        Ace your next systems design interview! Get tips, tricks, and practice interviews with a former hiring manager from Amazon, who interviewed thousands of software engineers and hired hundreds. Frank Kane will share the secrets of what your interviewer is looking for and the technologies you’re expected to know. Practice makes perfect, so you’ll also get six mock system design interviews with real-world interview questions from the biggest tech employers.

        A technical interview loop is a demanding process, and the system design part is often the most challenging. This course gets you prepared, and maximizes your odds of landing a new job that could change your life.

        About 5 hours of on-demand video content will cover what you need to know before starting your next interview:

        Techniques for scaling distributed systems and service fleets

        Database technologies and “NoSQL” solutions

        Use of caching to improve scalability and performance

        Designing for resiliency and handling failures
        
    """.trimIndent()
)
val course2 = CourseModel(
    4.5f,
    "Web Developer Course",
    R.drawable.webthumbnail,
    """ 
        Requirements
        Some prior experience in system architecture and computer science is expected.
        Description
        Ace your next systems design interview! Get tips, tricks, and practice interviews with a former hiring manager from Amazon, who interviewed thousands of software engineers and hired hundreds. Frank Kane will share the secrets of what your interviewer is looking for and the technologies you’re expected to know. Practice makes perfect, so you’ll also get six mock system design interviews with real-world interview questions from the biggest tech employers.

        A technical interview loop is a demanding process, and the system design part is often the most challenging. This course gets you prepared, and maximizes your odds of landing a new job that could change your life.

        About 5 hours of on-demand video content will cover what you need to know before starting your next interview:

        Techniques for scaling distributed systems and service fleets

        Database technologies and “NoSQL” solutions

        Use of caching to improve scalability and performance

        Designing for resiliency and handling failures
        
    """.trimIndent()
)
val course3 = CourseModel(
    5f,
    "Flutter Developer Course",
    R.drawable.download,
    """ 
        Requirements
        Some prior experience in system architecture and computer science is expected.
        Description
        Ace your next systems design interview! Get tips, tricks, and practice interviews with a former hiring manager from Amazon, who interviewed thousands of software engineers and hired hundreds. Frank Kane will share the secrets of what your interviewer is looking for and the technologies you’re expected to know. Practice makes perfect, so you’ll also get six mock system design interviews with real-world interview questions from the biggest tech employers.

        A technical interview loop is a demanding process, and the system design part is often the most challenging. This course gets you prepared, and maximizes your odds of landing a new job that could change your life.

        About 5 hours of on-demand video content will cover what you need to know before starting your next interview:

        Techniques for scaling distributed systems and service fleets

        Database technologies and “NoSQL” solutions

        Use of caching to improve scalability and performance

        Designing for resiliency and handling failures
        
    """.trimIndent()
)
val course4 = CourseModel(
    3.5f,
    "Git Course",
    R.drawable.online_learning
    ,
    """ 
        Requirements
        Some prior experience in system architecture and computer science is expected.
        Description
        Ace your next systems design interview! Get tips, tricks, and practice interviews with a former hiring manager from Amazon, who interviewed thousands of software engineers and hired hundreds. Frank Kane will share the secrets of what your interviewer is looking for and the technologies you’re expected to know. Practice makes perfect, so you’ll also get six mock system design interviews with real-world interview questions from the biggest tech employers.

        A technical interview loop is a demanding process, and the system design part is often the most challenging. This course gets you prepared, and maximizes your odds of landing a new job that could change your life.

        About 5 hours of on-demand video content will cover what you need to know before starting your next interview:

        Techniques for scaling distributed systems and service fleets

        Database technologies and “NoSQL” solutions

        Use of caching to improve scalability and performance

        Designing for resiliency and handling failures
        
    """.trimIndent()
)


val list= listOf(course1, course2, course3, course4)