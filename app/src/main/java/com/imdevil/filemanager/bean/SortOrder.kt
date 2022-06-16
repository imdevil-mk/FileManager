package com.imdevil.filemanager.bean

enum class SortOrder(val comparator: Comparator<FileInfo>) {
    NAME(Comparator<FileInfo> { m, n ->
        if (m.size == n.size) 0 else if (m.size > n.size) -1 else 1
    }),
    TIME(Comparator<FileInfo> { m, n ->
        if (m.dateModified == n.dateModified) 0 else if (m.dateModified > n.dateModified) -1 else 1
    }),
    SIZE(Comparator<FileInfo> { m, n ->
        if (m.size == n.size) 0 else if (m.size > n.size) -1 else 1
    }),
    MIMETYPE(Comparator<FileInfo> { m, n ->
        if (m.size == n.size) 0 else if (m.size > n.size) 1 else -1
    }),
    DIR(Comparator<FileInfo> { m, n ->
        if (m.size == n.size) 0 else if (m.size > n.size) 1 else -1
    }),
}