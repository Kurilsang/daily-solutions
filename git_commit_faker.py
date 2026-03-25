#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Git提交记录刷取工具
可以指定日期和次数来创建Git提交记录
"""

import os
import subprocess
import random
import argparse
from datetime import datetime, timedelta


def run_git_command(command, cwd=None, env=None):
    """执行Git命令"""
    merged_env = os.environ.copy()
    if env:
        merged_env.update(env)
    
    result = subprocess.run(
        command,
        shell=True,
        cwd=cwd,
        env=merged_env,
        capture_output=True,
        text=True,
        encoding='utf-8'
    )
    return result


def init_repo_if_needed(repo_path):
    """如果需要则初始化Git仓库"""
    git_dir = os.path.join(repo_path, '.git')
    if not os.path.exists(git_dir):
        print(f"初始化Git仓库: {repo_path}")
        run_git_command('git init', cwd=repo_path)
        return True
    return False


def generate_random_times(date, count):
    """生成指定日期内的随机时间列表"""
    times = []
    for _ in range(count):
        hour = random.randint(9, 22)  # 9点到22点之间
        minute = random.randint(0, 59)
        second = random.randint(0, 59)
        commit_time = date.replace(hour=hour, minute=minute, second=second)
        times.append(commit_time)
    
    # 按时间排序
    times.sort()
    return times


def create_fake_commit(repo_path, commit_time, commit_message=None):
    """创建一个指定时间的提交"""
    # 创建或修改一个文件
    fake_file = os.path.join(repo_path, 'activity.txt')
    
    with open(fake_file, 'a', encoding='utf-8') as f:
        f.write(f"Activity at {commit_time.isoformat()}\n")
    
    # 添加文件到暂存区
    run_git_command('git add .', cwd=repo_path)
    
    # 格式化时间
    # Git 接受的日期格式: "Sat Nov 14 14:00 2020 +0800"
    time_str = commit_time.strftime('%Y-%m-%dT%H:%M:%S')
    
    # 设置提交消息
    if not commit_message:
        messages = [
            "Update code",
            "Fix bug",
            "Add feature",
            "Refactor code",
            "Update documentation",
            "Code optimization",
            "Minor changes",
            "Improve performance",
            "Add tests",
            "Clean up code",
        ]
        commit_message = random.choice(messages)
    
    # 使用环境变量设置提交时间
    env = {
        'GIT_AUTHOR_DATE': time_str,
        'GIT_COMMITTER_DATE': time_str,
    }
    
    commit_cmd = f'git commit -m "{commit_message}"'
    result = run_git_command(commit_cmd, cwd=repo_path, env=env)
    
    if result.returncode == 0:
        print(f"✓ 提交成功: {commit_time.strftime('%Y-%m-%d %H:%M:%S')} - {commit_message}")
        return True
    else:
        print(f"✗ 提交失败: {result.stderr}")
        return False


def fake_commits_for_date(repo_path, date, count):
    """为指定日期创建多个提交"""
    print(f"\n{'='*50}")
    print(f"正在处理日期: {date.strftime('%Y-%m-%d')}, 提交次数: {count}")
    print('='*50)
    
    commit_times = generate_random_times(date, count)
    success_count = 0
    
    for commit_time in commit_times:
        if create_fake_commit(repo_path, commit_time):
            success_count += 1
    
    print(f"完成: {success_count}/{count} 个提交成功")
    return success_count


def fake_commits_for_date_range(repo_path, start_date, end_date, min_commits=1, max_commits=5):
    """为日期范围内的每一天创建提交"""
    current_date = start_date
    total_commits = 0
    
    while current_date <= end_date:
        # 随机决定每天的提交次数
        daily_count = random.randint(min_commits, max_commits)
        total_commits += fake_commits_for_date(repo_path, current_date, daily_count)
        current_date += timedelta(days=1)
    
    return total_commits


def parse_date(date_str):
    """解析日期字符串"""
    try:
        return datetime.strptime(date_str, '%Y-%m-%d')
    except ValueError:
        raise argparse.ArgumentTypeError(f"无效的日期格式: {date_str}，请使用 YYYY-MM-DD 格式")


def main():
    parser = argparse.ArgumentParser(
        description='Git提交记录刷取工具 - 为指定日期创建Git提交记录',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
使用示例:
  # 为今天创建5个提交
  python git_commit_faker.py -d today -c 5

  # 为指定日期创建3个提交
  python git_commit_faker.py -d 2024-03-20 -c 3

  # 为日期范围创建提交（每天1-5个随机）
  python git_commit_faker.py --start 2024-03-01 --end 2024-03-10

  # 为日期范围创建提交，指定每天提交次数范围
  python git_commit_faker.py --start 2024-03-01 --end 2024-03-10 --min 2 --max 8

  # 指定仓库路径
  python git_commit_faker.py -d today -c 5 -r /path/to/repo
        """
    )
    
    parser.add_argument('-d', '--date', type=str, 
                        help='指定日期 (格式: YYYY-MM-DD 或 "today")')
    parser.add_argument('-c', '--count', type=int, default=1,
                        help='提交次数 (默认: 1)')
    parser.add_argument('--start', type=str,
                        help='开始日期 (格式: YYYY-MM-DD)')
    parser.add_argument('--end', type=str,
                        help='结束日期 (格式: YYYY-MM-DD)')
    parser.add_argument('--min', type=int, default=1,
                        help='每天最少提交次数 (默认: 1)')
    parser.add_argument('--max', type=int, default=5,
                        help='每天最多提交次数 (默认: 5)')
    parser.add_argument('-r', '--repo', type=str, default='.',
                        help='Git仓库路径 (默认: 当前目录)')
    parser.add_argument('-m', '--message', type=str,
                        help='自定义提交消息 (不指定则随机生成)')
    
    args = parser.parse_args()
    
    # 获取仓库路径
    repo_path = os.path.abspath(args.repo)
    
    if not os.path.exists(repo_path):
        os.makedirs(repo_path)
        print(f"创建目录: {repo_path}")
    
    # 初始化仓库
    init_repo_if_needed(repo_path)
    
    # 处理日期范围模式
    if args.start and args.end:
        start_date = parse_date(args.start)
        end_date = parse_date(args.end)
        
        if start_date > end_date:
            print("错误: 开始日期不能晚于结束日期")
            return
        
        print(f"\n刷取日期范围: {args.start} 至 {args.end}")
        print(f"每天提交次数: {args.min} - {args.max}")
        
        total = fake_commits_for_date_range(
            repo_path, start_date, end_date, 
            args.min, args.max
        )
        print(f"\n{'='*50}")
        print(f"全部完成! 共创建 {total} 个提交")
    
    # 处理单日期模式
    elif args.date:
        if args.date.lower() == 'today':
            target_date = datetime.now()
        else:
            target_date = parse_date(args.date)
        
        fake_commits_for_date(repo_path, target_date, args.count)
    
    else:
        parser.print_help()
        print("\n错误: 请指定日期 (-d) 或日期范围 (--start 和 --end)")


if __name__ == '__main__':
    main()
